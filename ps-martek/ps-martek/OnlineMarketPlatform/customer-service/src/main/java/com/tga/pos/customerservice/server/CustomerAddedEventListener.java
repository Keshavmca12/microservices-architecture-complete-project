package com.tga.pos.customerservice.server;

import com.tga.customer.event.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerAddedEventListener {





        @KafkaListener(topics = "${kafka.consumer.customer-event-request-topic}", groupId = "${spring.kafka.consumer.group-id}")
        public void consumeMessage(CustomerAddedEvent event) {
            log.info("Got message <{}>", event);

            log.info("Updated customer  <{}>", event.getCustomer());
        }

//    @Override
//    public void consume(List<Event> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
//        log.info("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
//                messages.size(),
//                keys.toString(),
//                partitions.toString(),
//                offsets.toString());
//
//        inventoryService.deductInventory(messages.stream().map(message -> ((InventoryDeductedEvent) message).getInventory()).toList());
//    }
}




