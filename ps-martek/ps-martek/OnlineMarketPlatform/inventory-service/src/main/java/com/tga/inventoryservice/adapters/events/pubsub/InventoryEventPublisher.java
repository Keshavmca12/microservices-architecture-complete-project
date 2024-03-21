package com.tga.inventoryservice.adapters.events.pubsub;

import com.tga.inventory.event.InventoryDeductedEvent;
import com.tga.inventory.event.InventoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryEventPublisher {

    @Autowired
    KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public void publishToTopic(InventoryEvent event, String topic) {
        log.info("Sending InventoryUpdateEventResponse to topic: {} and Payload: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }


}
