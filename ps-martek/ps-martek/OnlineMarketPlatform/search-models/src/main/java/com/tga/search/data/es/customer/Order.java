package com.tga.search.data.es.customer;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String id;
    private String customerId;
    private String cartId;
    private List<Item> items;
    private OrderStatusEnum status; //ORDER_CREATED, ORDER_PAYMENT_SUCCESS, ORDER_PAYMENT_FAILED, ORDER_PLACED, ORDER_CANCELLED, ORDER_DELIVERED
    private Double totalPrice;
    private String addressId;
    private String comments;
    private Payment paymentDetail;
    private Shipment shipmentDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
