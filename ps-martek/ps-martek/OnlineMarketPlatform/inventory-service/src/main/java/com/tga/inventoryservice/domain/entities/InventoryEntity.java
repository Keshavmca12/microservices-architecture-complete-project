package com.tga.inventoryservice.domain.entities;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_id", updatable = false, nullable = false)
    private UUID inventoryId;

    @Column(name = "inventory_name")
    private String inventoryName;

    @Column(name = "product_id", nullable = false,unique = true)
    private String productId;

    @Column(name = "sku_id", nullable = false,unique = true)
    private String skuId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "price_unit", columnDefinition = "decimal")
    private Double priceUnit;

    @Column(name = "stock_quantity")
    private Integer quantity;
}
