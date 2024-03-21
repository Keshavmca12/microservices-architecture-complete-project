package org.pg.cartservice.adapters.out.helper;

import org.pg.cartservice.adapters.out.repository.entity.OrderAddressEntity;
import org.pg.cartservice.adapters.out.repository.entity.OrderEntity;
import org.pg.cartservice.adapters.out.repository.entity.OrderItem;
import org.pg.cartservice.adapters.out.repository.mapper.ModelMapperUtil;
import org.pg.cartservice.application.core.domain.Item;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.core.domain.OrderAddress;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

public interface OrderMappingHelper {

    public static OrderEntity map(final Order order) {
        return OrderEntity.builder()
                .id(order.getId() == null ? UUID.randomUUID().toString() : order.getId())
                .orderStatus(order.getStatus())
                .address(ModelMapperUtil.map(order.getAddress(), OrderAddressEntity.class))
                .items(order.getItems().stream().map(item -> {
                    return OrderItem.builder()
                            .id(item.getId() == null ? UUID.randomUUID().toString() : item.getId())
                            .sku(item.getSku())
                            .price(item.getPrice())
                            .productId(item.getProductId())
                            .productName(item.getProductName())
                            .quantity(item.getUnit())
                            .subTotal(item.getPrice().multiply(new BigDecimal(item.getUnit())))
                            .build();
                }).collect(Collectors.toList())).build();

    }

    public static Order map(final OrderEntity order) {
        return Order.builder()
                .id(order.getId() == null ? UUID.randomUUID().toString() : order.getId())
                .orderStatus(order.getOrderStatus())
                .address(ModelMapperUtil.map(order.getAddress(), OrderAddress.class))
                .items(order.getItems().stream().map(item -> {
                    return Item.builder()
                            .id(item.getId() == null ? UUID.randomUUID().toString() : item.getId())
                            .price(item.getPrice())
                            .sku(item.getSku())
                            .productId(item.getProductId())
                            .productName(item.getProductName())
                            .unit(item.getQuantity())
                            .subTotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                            .build();
                }).collect(Collectors.toList())).build();

    }


}
