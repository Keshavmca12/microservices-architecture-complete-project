package org.ps.inventoryservice.adapters.out.message;

import lombok.*;
import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderMessage {

    private Order order;
    private OrderEventEnum orderEventEnum;
}
