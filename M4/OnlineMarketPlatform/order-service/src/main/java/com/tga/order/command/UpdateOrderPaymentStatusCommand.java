package com.tga.order.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateOrderPaymentStatusCommand extends OrderCommand {
    private String status;
}
