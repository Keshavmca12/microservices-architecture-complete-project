package org.pg.cartservice.adapters.in.controller.request;

import org.pg.cartservice.application.core.domain.Item;
import org.pg.cartservice.application.core.domain.enums.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.List;


public class Order {

    private String id;
    private LocalDateTime orderDate;
    private List<Item> items;
    private PaymentDetails paymentDetails;
    private OrderStatusEnum orderStatus;
    private String userId;
    private OrderAddressRequest address;

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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    public OrderAddressRequest getAddress() {
        return address;
    }

    public void setAddress(OrderAddressRequest address) {
        this.address = address;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
