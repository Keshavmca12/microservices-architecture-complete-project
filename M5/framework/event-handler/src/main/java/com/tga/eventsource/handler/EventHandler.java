package com.tga.eventsource.handler;

import com.tga.eventsource.event.Event;
import com.tga.eventsource.event.NoActionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;


@Slf4j
@Component
public class EventHandler {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Commons Handler Listner
     * @param record
     */
    @KafkaListener(topics = "#{'${crossdomain_eventTopics}'.split(',')}")
    public void messageListener(ConsumerRecord<String, String> record){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(record.value());
            //log.info("Key: "+ record.key() +", Value:"+  jsonNodeRoot.toPrettyString());

            String payload = jsonNodeRoot.get("payload").toString();
            JsonNode eventDetails = jsonNodeRoot.get("payload").get("after").get("properties");

            if(NoActionEvent.class.getName().equals(eventDetails.get("eventType").asText())) {
                log.info("Ignoring NoActionEvent for processing");
                return;
            }
            Event event =  (Event) mapper.readValue(eventDetails.get("eventData").asText(), Class.forName(eventDetails.get("eventType").asText()));

            String aggregateName = Class.forName(eventDetails.get("entityType").asText()).getSimpleName();
            String entityId = eventDetails.get("entityId").asText();

            log.info("aggregateName:"+ aggregateName);
            EventListener eventListener = (EventListener) applicationContext.getBean(aggregateName);

            eventListener.getClass().getMethod("handle", event.getClass()).invoke(eventListener, event);

        } catch (JsonProcessingException  | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }catch ( ClassNotFoundException | NoSuchMethodException e) {
           log.info("No class or method definition in the lister class for event, Error {} ", e.getMessage());
        }
    }

}
