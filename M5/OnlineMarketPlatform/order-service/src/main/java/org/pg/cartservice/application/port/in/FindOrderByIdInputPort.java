package org.pg.cartservice.application.port.in;



import org.pg.cartservice.application.core.domain.Order;

public interface FindOrderByIdInputPort {

    Order execute(String id);
}
