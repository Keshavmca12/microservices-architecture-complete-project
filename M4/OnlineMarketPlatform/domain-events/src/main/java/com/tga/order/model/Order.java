package com.tga.order.model;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@ToString
public class Order  {

    private String id;
    private String customerId;
    private String cartId;
    private List<Item> items;
    private OrderStatusEnum status; //ORDER_CREATED, ORDER_PAYMENT_SUCCESS, ORDER_PAYMENT_FAILED, ORDER_PLACED, ORDER_CANCELLED, ORDER_DELIVERED
    private Double totalPrice;
    private String addressId;
    private String comments;
}
