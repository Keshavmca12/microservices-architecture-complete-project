package org.pg.cartservice.application.core.usecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.in.FinaliseOrderInputPort;
import org.pg.cartservice.application.port.out.UpdateOrderOutputPort;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinalizeOrderUseCase implements FinaliseOrderInputPort {


    private final FindOrderByIdUseCase findOrderByIdUseCase;

    private final UpdateOrderOutputPort updateOrderOutputPort;

    @Override
    public void execute(final Order order) {
        Order orderfromDB = this.findOrderByIdUseCase.execute(order.getId());
        orderfromDB.statusFinalized();
        this.updateOrderOutputPort.execute(orderfromDB);
    }
}
