package com.tga.pos.customerservice.adapter.events.pubsub;

import com.tga.customer.event.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@Slf4j
public class CustomerAddedPublisher {

    @Autowired
    KafkaTemplate<String, CustomerAddedEvent> kafkaTemplate;

    public void publishToTopic(CustomerAddedEvent event, String topic) {
        log.info("Sending InventoryUpdateEventResponse to topic: {} and Payload: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }


}
