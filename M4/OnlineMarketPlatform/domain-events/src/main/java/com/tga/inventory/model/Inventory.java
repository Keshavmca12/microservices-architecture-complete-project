package com.tga.inventory.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Inventory {
    private UUID inventoryId;
    private String inventoryName;
    private String productId;
    private String skuId;
    private String supplierId;
    private Double priceUnit;
    private Integer quantity;
    private InventoryUpdateStatus inventoryUpdateStatus;
}
