package org.pg.cartservice.application.port.out;


import org.pg.cartservice.application.core.domain.Order;

public interface UpdateOrderOutputPort {

    void execute(Order order);
}
