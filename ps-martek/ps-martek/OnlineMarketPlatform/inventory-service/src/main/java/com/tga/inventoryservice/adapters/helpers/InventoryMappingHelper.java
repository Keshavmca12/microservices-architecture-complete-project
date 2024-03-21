package com.tga.inventoryservice.adapters.helpers;

import com.tga.inventory.model.Inventory;
import com.tga.inventoryservice.domain.entities.InventoryEntity;

public class InventoryMappingHelper {

    public static InventoryEntity map(final Inventory inventory) {
        return InventoryEntity.builder()
                .inventoryId(inventory.getInventoryId())
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
