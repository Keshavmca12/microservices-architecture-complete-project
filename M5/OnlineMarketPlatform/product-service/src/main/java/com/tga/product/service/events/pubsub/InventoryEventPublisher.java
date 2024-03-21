package com.tga.product.service.events.pubsub;

import com.tga.inventory.event.InventoryAddedEvent;
import com.tga.product.event.ProductAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryEventPublisher {

    @Autowired
    KafkaTemplate<String, InventoryAddedEvent> kafkaTemplate;

    public void publishInventory(InventoryAddedEvent event,String topic) {
        log.info("Sending ProductAddedEvent to topic: {} and Payload: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
    

//    public void publishToOrderTopic(InventoryUpdateResponseEvent event) {
//        log.info("Sending InventoryUpdateEventResponse to topic: {} and Payload: {}", topic, event);
//        kafkaTemplate.send(topic, event);
//    }

}
