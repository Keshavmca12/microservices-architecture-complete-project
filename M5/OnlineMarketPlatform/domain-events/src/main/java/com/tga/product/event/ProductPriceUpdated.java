package com.tga.product.event;

import com.tga.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductPriceUpdated implements ProductEvent{

    private Product product;

}