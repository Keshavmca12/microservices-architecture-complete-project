package com.tga.eventsource.handler;

import com.tga.eventsource.aggregate.AggregateRepository;
import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.model.EventStore;
import com.tga.eventsource.repo.EventStoreRepository;
import com.tga.eventsource.strategy.SnapshotStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractEventHandler {

    @Autowired
    private EventStoreRepository esRepo;

    @Autowired
    private SnapshotStrategy snapshotStrategy;

    @Autowired
    private ApplicationContext applicationContext;

    public abstract List<Event> handleEvents(Aggregate aggregate, Event event);

    public void producer(ConsumerRecord<String,String>record){

    }

    @KafkaListener(topics = "${eventTopics}")
    public void messageListener(ConsumerRecord<String, String> record){
        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(record.value());
            log.info("Key: "+ record.key() +", Value:"+  jsonNodeRoot.toPrettyString());

            String payload = jsonNodeRoot.get("payload").toString();
            JsonNode eventDetails = jsonNodeRoot.get("payload").get("after").get("properties");
            Event event =  (Event) mapper.readValue(eventDetails.get("eventData").asText(), Class.forName(eventDetails.get("eventType").asText()));

            String aggregateName = Class.forName(eventDetails.get("entityType").asText()).getSimpleName();
            String entityId = eventDetails.get("entityId").asText();
            log.info("aggregateName:"+ aggregateName);
            AggregateRepository aggregateRepository = (AggregateRepository) applicationContext.getBean(aggregateName);
            Optional<Aggregate> aggregateOpt = aggregateRepository.loadAggregateByEntityId(entityId);
            List<Event> events = handleEvents(aggregateOpt.get(), event);

            Long lastProcessedEventId = 0l;
            for (Event eventOccurred : events) {
                EventStore es = new EventStore();
                es.setEventType(eventOccurred.getClass().getCanonicalName());
                es.setEntityType(aggregateOpt.get().getClass().getCanonicalName());
                es.setEntityId(entityId);
                es.setEventData(new ObjectMapper().writeValueAsString(eventOccurred));
                log.info("Storing event type: {}", es.getEventType());
                lastProcessedEventId = esRepo.save(es).getId();
            }
            snapshotStrategy.possibleSnapshot(aggregateOpt.get(), events, lastProcessedEventId);

        } catch (JsonProcessingException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
