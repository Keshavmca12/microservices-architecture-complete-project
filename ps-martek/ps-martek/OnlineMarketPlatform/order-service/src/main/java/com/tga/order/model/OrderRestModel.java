package com.tga.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRestModel {
    //private String productId;
    private String userId;
    private String addressId;
    List<Item> itemList;
    //private Integer quantity;
}
