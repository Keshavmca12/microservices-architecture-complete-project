package org.pg.cartservice.application.core.usecase;

import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;
import org.pg.cartservice.application.port.in.CreateOrderInputPort;
import org.pg.cartservice.application.port.out.SaveOrderOutputPort;
import org.pg.cartservice.application.port.out.SendCreateOrderOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderUseCase implements CreateOrderInputPort {

    private final SaveOrderOutputPort saveCartOutputPort;
    private final SendCreateOrderOutputPort sendCreateOrderOutputPort;


    public CreateOrderUseCase(SaveOrderOutputPort saveCartOutputPort, SendCreateOrderOutputPort sendCreateOrderOutputPort) {
        this.saveCartOutputPort = saveCartOutputPort;
        this.sendCreateOrderOutputPort = sendCreateOrderOutputPort;
    }

    @Override
    public void execute(final Order order) {
        order.statusInitial();
        final Order savedOrder = this.saveCartOutputPort.execute(order);
        savedOrder.setPaymentDetails(order.getPaymentDetails());
        this.sendCreateOrderOutputPort.execute(savedOrder, OrderEventEnum.CREATED_ORDER);
    }
}
