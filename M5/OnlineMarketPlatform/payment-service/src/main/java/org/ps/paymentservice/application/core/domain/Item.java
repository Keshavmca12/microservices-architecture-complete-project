package org.ps.paymentservice.application.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
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
