package com.tga.inventoryservice.adapters.dto.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckInventoryResponse {
    private String skuId;
    private boolean isInStock;
}
