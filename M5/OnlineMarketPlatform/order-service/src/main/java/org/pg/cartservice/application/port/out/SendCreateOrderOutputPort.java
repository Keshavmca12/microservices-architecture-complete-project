package org.pg.cartservice.application.port.out;


import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;

public interface SendCreateOrderOutputPort {

    void execute(Order order, OrderEventEnum orderEventEnum);
}
