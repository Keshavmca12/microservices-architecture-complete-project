package com.tga.inventoryservice.adapters.service;

import com.tga.inventory.model.Inventory;
import com.tga.inventory.model.InventoryUpdateStatus;
import com.tga.inventoryservice.adapters.helpers.InventoryMappingHelper;
import com.tga.inventoryservice.adapters.repository.InventoryRepository;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.tga.inventoryservice.adapters.dto.query.CheckInventoryResponse;
import com.tga.inventoryservice.domain.entities.InventoryEntity;
import com.tga.inventoryservice.domain.ports.InventoryService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll().stream().map(InventoryMappingHelper::map).distinct().collect(Collectors.toList());
    }

    @Override
    public Inventory findById(UUID inventoryId) {
        return null;
    }

    @Override
    public Inventory save(Inventory inventory) {
        log.info("*** save inventory *");
        InventoryEntity inventoryEntity = InventoryMappingHelper.map(inventory);
        inventoryEntity.setInventoryId(UUID.randomUUID());
        inventoryEntity.setCreatedAt(Instant.now());
        inventoryEntity.setUpdatedAt(Instant.now());
        return InventoryMappingHelper.map(this.inventoryRepository.save(inventoryEntity));
    }

    @Override
    public List<Inventory> save(List<Inventory> inventories) {
        log.info("*** save inventories *");
        return inventories.stream().map(inventory -> {
            InventoryEntity inventoryEntity = InventoryMappingHelper.map(inventory);
            inventoryEntity.setInventoryId(UUID.randomUUID());
            inventoryEntity.setCreatedAt(Instant.now());
            inventoryEntity.setUpdatedAt(Instant.now());
            Inventory savedEntity = InventoryMappingHelper.map(this.inventoryRepository.save(inventoryEntity));
            return savedEntity;
        }).collect(Collectors.toList());
    }

    @Override
    public Inventory update(Inventory inventory) {
        log.info("*** save inventory *");
        return InventoryMappingHelper.map(this.inventoryRepository.save(InventoryMappingHelper.map(inventory)));
    }

    @Override
    public List<Inventory> update(List<Inventory> inventories) {
        log.info("*** update inventory *");
        return inventories.stream().map(inventory -> {
            InventoryEntity inventoryEntity = InventoryMappingHelper.map(inventory);
            inventoryEntity.setUpdatedAt(Instant.now());
            Inventory savedEntity = InventoryMappingHelper.map(this.inventoryRepository.save(inventoryEntity));
            return savedEntity;
        }).collect(Collectors.toList());

    }

    @Override
    public Inventory update(UUID inventoryId, Inventory inventory) {
        log.info("*** update inventory with inventory id *" + inventoryId);
        return InventoryMappingHelper.map(this.inventoryRepository.save(
                InventoryMappingHelper.map(this.findById(inventoryId))));
    }

    @Override
    public List<CheckInventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory Stock");
        List<InventoryEntity> inventoryEntities = inventoryRepository.findBySkuIdIn(skuCode);
        return inventoryEntities.stream().map(inventory ->
                CheckInventoryResponse.builder()
                        .skuId(inventory.getSkuId())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
        ).collect(Collectors.toList());

    }

    @Override
    public void deleteById(UUID inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }


    @Override
    public Inventory deductInventory(Inventory inventory) {
        if (inventoryRepository.deductQuantity(inventory.getQuantity(), inventory.getSkuId()) == 1) {
            inventory.setInventoryUpdateStatus(InventoryUpdateStatus.DEDUCTED);
        } else {
            inventory.setInventoryUpdateStatus(InventoryUpdateStatus.FAILED);
        }
        return inventory;
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        if (inventoryRepository.addQuantity(inventory.getQuantity(), inventory.getSkuId()) == 1) {
            inventory.setInventoryUpdateStatus(InventoryUpdateStatus.ADDED);
        } else {
            inventory.setInventoryUpdateStatus(InventoryUpdateStatus.FAILED);
        }
        return inventory;
    }

    @Override
    public List<Inventory> deductInventories(List<Inventory> inventories) {
        return inventories.stream().map(inventory -> {
            if (inventoryRepository.deductQuantity(inventory.getQuantity(), inventory.getSkuId()) == 1) {
                inventory.setInventoryUpdateStatus(InventoryUpdateStatus.ADDED);
                return inventory;
            } else {
                inventory.setInventoryUpdateStatus(InventoryUpdateStatus.FAILED);
            }
            return inventory;
        }).collect(Collectors.toList());

    }

    @Override
    public List<Inventory> restoreInventories(List<Inventory> inventories) {
        return inventories.stream().map(inventory -> {
            if (inventoryRepository.addQuantity(inventory.getQuantity(), inventory.getSkuId()) == 1) {
                inventory.setInventoryUpdateStatus(InventoryUpdateStatus.ADDED);
                return inventory;
            } else {
                inventory.setInventoryUpdateStatus(InventoryUpdateStatus.FAILED);
            }
            return inventory;
        }).collect(Collectors.toList());
    }
}
