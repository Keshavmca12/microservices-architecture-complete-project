package org.pg.cartservice.application.core.usecase;

import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.in.CancelOrderInputPort;
import org.pg.cartservice.application.port.out.UpdateOrderOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderUseCase implements CancelOrderInputPort {

    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final UpdateOrderOutputPort updateOrderOutputPort;

    public CancelOrderUseCase(FindOrderByIdUseCase findOrderByIdUseCase, UpdateOrderOutputPort updateOrderOutputPort) {
        this.findOrderByIdUseCase = findOrderByIdUseCase;
        this.updateOrderOutputPort = updateOrderOutputPort;
    }


    @Override
    public void execute(final Order order) {
        Order existingOrder = this.findOrderByIdUseCase.execute(order.getId());
        if (existingOrder == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        existingOrder.statusCanceled();
        this.updateOrderOutputPort.execute(existingOrder);
    }
}
