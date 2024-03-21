package org.pg.cartservice.adapters.out.message;

import lombok.*;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderMessage {

    private Order order;
    private OrderEventEnum orderEventEnum;
}
