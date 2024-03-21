package org.ps.inventoryservice.application.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Item {
    private String id;
    private String productId;
    private String productName;
    private String sku;
    private int unit;
    private BigDecimal price;
    private boolean isoutofStock;
    private BigDecimal subTotal;

}
