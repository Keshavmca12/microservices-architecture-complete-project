package org.pg.cartservice.application.port.in;


import org.pg.cartservice.application.core.domain.Order;

public interface FinaliseOrderInputPort {
    void execute(Order order);
}
