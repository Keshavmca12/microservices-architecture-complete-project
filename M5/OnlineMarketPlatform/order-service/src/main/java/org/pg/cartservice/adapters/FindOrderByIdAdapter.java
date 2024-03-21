package org.pg.cartservice.adapters;

import lombok.RequiredArgsConstructor;
import org.pg.cartservice.adapters.in.exception.wrapper.OrderNotFoundException;
import org.pg.cartservice.adapters.out.helper.OrderMappingHelper;
import org.pg.cartservice.adapters.out.repository.OrderRepository;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.out.FindOrderByIdOutputPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOrderByIdAdapter implements FindOrderByIdOutputPort {

    private final OrderRepository orderRepository;

    @Override
    public Order execute(final String id) {
        return this.orderRepository.findById(id)
                .map(o -> OrderMappingHelper.map(o))
                .orElseThrow(() ->
                        new OrderNotFoundException(String
                                .format("Order with id: %s not found", id)));


    }


}
