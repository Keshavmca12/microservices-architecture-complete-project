package com.tga.product.service.events.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tga.product.event.ProductAddedEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductEventPublisher {

    @Autowired
    KafkaTemplate<String, ProductAddedEvent> kafkaTemplate;
    



    public void publishToTopic(ProductAddedEvent event,String topic) {
        log.info("Sending ProductAddedEvent to topic: {} and Payload: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }

//    public void publishToOrderTopic(InventoryUpdateResponseEvent event) {
//        log.info("Sending InventoryUpdateEventResponse to topic: {} and Payload: {}", topic, event);
//        kafkaTemplate.send(topic, event);
//    }

}
