package org.pg.cartservice.application.port.in;


import org.pg.cartservice.application.core.domain.Order;

public interface CreateOrderInputPort {
    void execute(Order order);
}
