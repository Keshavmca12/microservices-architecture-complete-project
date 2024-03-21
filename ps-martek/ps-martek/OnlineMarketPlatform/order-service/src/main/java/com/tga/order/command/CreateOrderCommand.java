package com.tga.order.command;

import com.tga.order.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CreateOrderCommand extends OrderCommand {

    String customerId;
    String cartId;
    List<Item> items;
    String addressId;

}
