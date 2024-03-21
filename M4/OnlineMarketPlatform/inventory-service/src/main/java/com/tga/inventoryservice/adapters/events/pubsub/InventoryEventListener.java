package com.tga.inventoryservice.adapters.events.pubsub;

import com.tga.inventory.event.InventoryAddedEvent;
import com.tga.inventory.event.InventoryDeductedEvent;
import com.tga.inventory.event.InventoryRestoreEvent;
import com.tga.inventory.event.ProductPriceChangeEvent;
import com.tga.inventory.model.Inventory;
import com.tga.inventoryservice.domain.ports.InventoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.beans.EventHandler;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryEventListener {

    private final InventoryService inventoryService;


    private final InventoryEventPublisher eventPublisher;

    @Value("${kafka.producer.order-event-response-topic}")
    private String orderEventTopic;

    @KafkaListener(topics = "${kafka.consumer.inventory-restore-request-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void handle(InventoryRestoreEvent event) {
        log.info("Got message for Deduct Inventory<{}>", event);
        List<Inventory> updatedDtoList = inventoryService.restoreInventories(event.getInventoryList());
        log.info("Deducted inventory List <{}>", updatedDtoList);
        eventPublisher.publishToTopic(event, orderEventTopic);
    }


    @KafkaListener(topics = "${kafka.consumer.inventory-deduction-request-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void handle(InventoryDeductedEvent event) {
        log.info("Got message for Deduct Inventory<{}>", event);
        List<Inventory> updatedDtoList = inventoryService.deductInventories(event.getInventoryList());
        log.info("Deducted inventory List <{}>", updatedDtoList);
        eventPublisher.publishToTopic(event, orderEventTopic);
    }

    @KafkaListener(topics = "${kafka.consumer.inventory-added-request-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void handle(InventoryAddedEvent event) {
        log.info("Got message to Add inventories<{}>", event);
        List<Inventory> updatedDtoList = inventoryService.update(event.getInventoryList());
        log.info("Added inventory List <{}>", updatedDtoList);
    }

    @KafkaListener(topics = "${kafka.consumer.price-update-request-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void handle(ProductPriceChangeEvent event) {
        log.info("Got message for Price Change<{}>", event);
        List<Inventory> updatedDtoList = inventoryService.update(event.getInventoryList());
        log.info("Price Change inventory List <{}>", updatedDtoList);
    }
}

