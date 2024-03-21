package com.tga.inventoryservice.adapters.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {

    private UUID inventoryId;
    private String inventoryName;
    private String productId;
    private String skuId;
    private String supplierId;
    private Double priceUnit;
    private Integer quantity;
}
