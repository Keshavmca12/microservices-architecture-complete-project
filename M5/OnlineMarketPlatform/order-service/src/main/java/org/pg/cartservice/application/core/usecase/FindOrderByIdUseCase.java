package org.pg.cartservice.application.core.usecase;


import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.in.FindOrderByIdInputPort;
import org.pg.cartservice.application.port.out.FindOrderByIdOutputPort;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdUseCase implements FindOrderByIdInputPort {

    private final FindOrderByIdOutputPort findOrderByIdOutputPort;

    public FindOrderByIdUseCase(FindOrderByIdOutputPort findOrderByIdOutputPort) {
        this.findOrderByIdOutputPort = findOrderByIdOutputPort;
    }


    @Override
    public Order execute(final String id) {
        Order order = this.findOrderByIdOutputPort.execute(id);

        return order;
    }
}
