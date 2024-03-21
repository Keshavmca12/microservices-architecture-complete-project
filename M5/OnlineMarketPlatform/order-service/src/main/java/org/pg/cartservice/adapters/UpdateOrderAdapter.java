package org.pg.cartservice.adapters;


import lombok.RequiredArgsConstructor;
import org.pg.cartservice.adapters.out.helper.OrderMappingHelper;
import org.pg.cartservice.adapters.out.repository.OrderRepository;
import org.pg.cartservice.adapters.out.repository.entity.OrderEntity;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.out.UpdateOrderOutputPort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateOrderAdapter implements UpdateOrderOutputPort {

    private final OrderRepository orderRepository;

    @Override
    public void execute(final Order order) {
        final OrderEntity orderEntity = OrderMappingHelper.map(order);
        mapChildClass(orderEntity);

        //orderEntity.setOrderStatus(order.getStatus());
        orderRepository.updateOrderStatus(orderEntity.getOrderStatus().toString(),orderEntity.getId());
       // return OrderMappingHelper.map(orderEntityDB);
    }

    private static void mapChildClass(OrderEntity orderEntity) {

        orderEntity.getItems().stream().forEach(item -> {
            item.setOrder(orderEntity);
        });
        if (orderEntity.getAddress() != null && orderEntity.getAddress().getId() == null) {
            orderEntity.getAddress().setId(UUID.randomUUID().toString());
            orderEntity.getAddress().setOrder(orderEntity);
        }
        orderEntity.setUpdatedAt(Instant.now());
    }
}
