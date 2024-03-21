package com.tga.order.event;

import com.tga.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderOnHoldEvent implements OrderEvent{

    private Order order;

}