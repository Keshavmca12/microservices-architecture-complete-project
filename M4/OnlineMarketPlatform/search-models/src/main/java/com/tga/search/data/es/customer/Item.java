package com.tga.search.data.es.customer;


import com.tga.search.data.es.product.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Item {

    private Product product;
    private int unit;
    private double price;
}