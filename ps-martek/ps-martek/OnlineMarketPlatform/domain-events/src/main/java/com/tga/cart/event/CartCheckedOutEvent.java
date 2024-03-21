package com.tga.cart.event;

import com.tga.cart.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CartCheckedOutEvent implements CartEvent{
    private Cart cart;
}
