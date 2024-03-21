package org.ps.paymentservice.adapters.out.message;

import lombok.*;
import org.ps.paymentservice.application.core.domain.Order;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderMessage {

    private Order order;
    private OrderEventEnum orderEventEnum;
}
