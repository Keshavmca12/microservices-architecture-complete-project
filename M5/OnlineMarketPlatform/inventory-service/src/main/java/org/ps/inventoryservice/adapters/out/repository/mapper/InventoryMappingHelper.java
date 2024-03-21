package org.ps.inventoryservice.adapters.out.repository.mapper;


import org.ps.inventoryservice.adapters.out.repository.entity.InventoryEntity;
import org.ps.inventoryservice.application.core.domain.Inventory;

import java.util.UUID;

public class InventoryMappingHelper {

    public static InventoryEntity map(final Inventory inventory) {
        return InventoryEntity.builder()
                .inventoryId(inventory.getInventoryId()==null? UUID.randomUUID().toString():inventory.getInventoryId())
                .inventoryName(inventory.getInventoryName())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .priceUnit(inventory.getPriceUnit())
                .skuId(inventory.getSkuId())
                .quantity(inventory.getQuantity()).build();
    }

    public static Inventory map(final InventoryEntity inventory) {
        return Inventory.builder()
                .inventoryId(inventory.getInventoryId())
                .inventoryName(inventory.getInventoryName())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .priceUnit(inventory.getPriceUnit())
                .skuId(inventory.getSkuId())
                .quantity(inventory.getQuantity()).build();
    }
}
