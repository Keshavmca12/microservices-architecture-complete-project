package org.pg.cartservice.application.core.domain;

import lombok.*;
import org.pg.cartservice.application.core.domain.enums.OrderStatusEnum;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {

    private String id;
    private List<Item> items;
    private PaymentDetails paymentDetails;
    private OrderStatusEnum orderStatus;
    private String userId;
    private OrderAddress address;

    private OrderStatusEnum status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void statusInitial() {
        this.status = OrderStatusEnum.PENDING;
    }

    public void statusFinalized() {
        this.status = OrderStatusEnum.FINALIZED;
    }

    public void statusCanceled() {
        this.status = OrderStatusEnum.CANCELED;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderAddress getAddress() {
        return address;
    }

    public void setAddress(OrderAddress address) {
        this.address = address;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
