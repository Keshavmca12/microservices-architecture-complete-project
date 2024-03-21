package org.pg.cartservice.adapters.out.repository.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Entity
@ToString
public class OrderItem {
    @Id
    @Column(name = "item_id", updatable = false, nullable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private String productId;
    private String sku;
    private String productName;

    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;
    private int version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return id.equals(that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}
