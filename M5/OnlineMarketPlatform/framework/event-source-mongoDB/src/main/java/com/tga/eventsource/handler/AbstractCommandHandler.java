package com.tga.eventsource.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.command.Command;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.strategy.SnapshotStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@Slf4j
public abstract class AbstractCommandHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SnapshotStrategy snapshotStrategy;


    public abstract List<Event> handleCommand(Command command);

    public abstract Class<? extends Aggregate> aggregateClassName();

    @KafkaListener(topics = "${commandTopics}")
    public void messageListener(ConsumerRecord<String, String> record){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNodeRoot = objectMapper.readTree(record.value());
            String payload = jsonNodeRoot.get("payload").toString();
            JsonNode metaNode = jsonNodeRoot.get("meta");
            Command command = (Command) objectMapper.readValue(payload, Class.forName(metaNode.get("commandClass").asText()));

            List<Event> events = handleCommand(command);

//            Long lastProcessedEventId = 0l;
//            for (Event event : events) {
//                EventStore es = new EventStore();
//                es.setEventType(event.getClass().getCanonicalName());
//                es.setEntityType(aggregateClassName().getCanonicalName());
//                es.setEntityId(aggregate.getId());
//                es.setEventData(new ObjectMapper().writeValueAsString(event));
//                lastProcessedEventId = esRepo.save(es).getId();
//            }
//            snapshotStrategy.possibleSnapshot(aggregate, events, lastProcessedEventId);

        } catch (JsonProcessingException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
