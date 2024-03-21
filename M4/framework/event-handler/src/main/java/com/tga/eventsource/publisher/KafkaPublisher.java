package com.tga.eventsource.publisher;

import com.tga.eventsource.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaPublisher {
    @Autowired
    KafkaTemplate<String, Event> kafkaTemplate;

    public void producer(Event event,String topic) {
        log.info("Sending ProductAddedEvent to topic: {} and Payload: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
}
