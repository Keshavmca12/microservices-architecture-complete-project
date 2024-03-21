package com.tga.cart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart  {

    private String id;
    private String customerId;
    private String addressId;
    private List<Item> items;
    private Double totalPrice;
    private boolean isCheckedOut;


}
