package com.tga.inventoryservice.adapters.controllers.v1;

import com.tga.inventory.event.InventoryDeductedEvent;
import com.tga.inventory.event.InventoryRestoreEvent;
import com.tga.inventoryservice.adapters.events.pubsub.InventoryEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
@RequiredArgsConstructor
@Slf4j
//todo  this calss is for testing will remove this
public class Publisher {
    private final InventoryEventPublisher inventoryEventPublisher;
    @Value("${kafka.consumer.inventory-deduction-request-topic}")
    private String deductTopic;

    @Value("${kafka.consumer.inventory-restore-request-topic}")
    private String restoreTopic;

    @PostMapping
    public String publish(@RequestBody InventoryDeductedEvent inventoryDeductedEvent) {
        log.info("Received InventoryDeductedEvent: {}", inventoryDeductedEvent);
        inventoryEventPublisher.publishToTopic(inventoryDeductedEvent, deductTopic);
        return "send deduct event successfully";

    }

    @PostMapping("/restore")
    public String publishtoRestoreTopic(@RequestBody InventoryRestoreEvent inventoryRestoreEvent) {
        log.info("Received inventory check request for inventories: {}", inventoryRestoreEvent);
        inventoryEventPublisher.publishToTopic(inventoryRestoreEvent, restoreTopic);
        return "send restore event successfully";

    }
}
