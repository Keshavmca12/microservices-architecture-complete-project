package org.ps.inventoryservice.adapters.out.repository.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class InventoryEntity extends AbstractMappedEntity implements Serializable {

    @Id
    @Column(name = "inventory_id", updatable = false, nullable = false)
    private String inventoryId;

    @Column(name = "inventory_name")
    private String inventoryName;

    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "sku_id", nullable = false, unique = true)
    private String skuId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "price_unit", columnDefinition = "decimal")
    private BigDecimal priceUnit;

    @Column(name = "stock_quantity")
    private Integer quantity;
}
