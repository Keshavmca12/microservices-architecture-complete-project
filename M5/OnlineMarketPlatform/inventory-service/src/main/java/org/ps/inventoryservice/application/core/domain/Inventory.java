package org.ps.inventoryservice.application.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Inventory {

    private String inventoryId;
    private String inventoryName;
    private String productId;
    private String skuId;
    private String supplierId;
    private BigDecimal priceUnit;
    private Integer quantity;
    public void debitQuantity(final int quantity) {
        this.quantity -= quantity;
    }

    public void creditQuantity(final int quantity) {
        this.quantity += quantity;
    }

}
