package com.tga.order.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UpdateOrderDeliverStatusCommand extends OrderCommand{

    String shipmentId;
    String status;
}
