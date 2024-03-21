package org.pg.cartservice.adapters;


import lombok.RequiredArgsConstructor;
import org.pg.cartservice.adapters.out.helper.OrderMappingHelper;
import org.pg.cartservice.adapters.out.repository.OrderRepository;
import org.pg.cartservice.adapters.out.repository.entity.OrderEntity;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.out.SaveOrderOutputPort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveOrderAdapter implements SaveOrderOutputPort {

    private final OrderRepository orderRepository;

    @Override
    public Order execute(final Order order) {
        final OrderEntity orderEntity = OrderMappingHelper.map(order);
        mapChildClass(orderEntity);

        //orderEntity.setOrderStatus(order.getStatus());
        OrderEntity orderEntityDB = orderRepository.save(orderEntity);
        return OrderMappingHelper.map(orderEntityDB);
    }

    private static void mapChildClass(OrderEntity orderEntity) {

        orderEntity.getItems().stream().forEach(item -> {
            item.setOrder(orderEntity);
        });
        if (orderEntity.getAddress() != null && orderEntity.getAddress().getId() == null) {
            orderEntity.getAddress().setId(UUID.randomUUID().toString());
            orderEntity.getAddress().setOrder(orderEntity);
        }
        orderEntity.setCreatedAt(Instant.now());
    }
}
