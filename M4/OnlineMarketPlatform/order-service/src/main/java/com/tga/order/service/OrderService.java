package com.tga.order.service;

import com.tga.eventsource.aggregate.AggregateRepository;
import com.tga.eventsource.publisher.KafkaPublisher;
import com.tga.order.command.CreateOrderCommand;
import com.tga.order.command.OrderCommand;
import com.tga.order.event.OrderCreatedEvent;
import com.tga.order.event.OrderPlacedEvent;
import com.tga.order.model.Order;
import com.tga.order.model.OrderStatusEnum;
import com.tga.order.repository.OrderRepository;
import com.tga.order.utils.ModelMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
// All business code which needs to be transactional manner to be placed here with method level @Transactional()
// Do not handle any exception which overrules the Rollback functionality
public class OrderService {

    @Autowired
    private AggregateRepository<Order, OrderCommand> aggregateRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaPublisher publisher;

    /**
     *
     * @param orderId
     * @return
     */
    public Optional<Order> getOrder(@NotNull String orderId) {
        if(orderId == null){
            return Optional.ofNullable(null);
        }
        return aggregateRepository.loadAggregateByEntityId(orderId);
    }

    /**
     *
     * @param cmd
     * @return
     */
    @Transactional()
    public Order createOrder(CreateOrderCommand cmd) {
        log.info("OrderService.createOrder: {}", cmd);


        Order order = ModelMapperUtil.map(cmd, Order.class);
        order.setId(UUID.randomUUID().toString());
        order.setStatus(OrderStatusEnum.ORDER_PLACED);
        order.setTotalPrice(order.getItems().stream().collect(Collectors.summingDouble(value -> value.getUnit()* value.getPrice())));
        order = orderRepository.insert(order);

//        order.applyEvent(new OrderPlacedEvent());
        OrderCreatedEvent event=new OrderCreatedEvent();
        event.setOrder(order);

        publisher.producer(event,"orderEvents");

        // place other business logic here. Mainly one which required coordination with other services/resources
        return order;
    }

    /**
     *
     * @param cmd
     */
    /*public Order updateOrderPaymentStatus(UpdateOrderPaymentStatusCommand cmd) {
        log.info("OrderService.updateOrderPaymentStatus: {}", cmd);
        AggregateWithEvents<Order> orderWithEvents = aggregateRepository.save(cmd);
        // place other business logic here. Mainly one which required coordination with other services/resources
        return orderWithEvents.getAggregate();
    }


    public Order updateOrderDeliveryStatus(UpdateOrderDeliverStatusCommand cmd) {
        log.info("OrderService.updateOrderDeliveryStatus: {}", cmd);
        AggregateWithEvents<Order> orderWithEvents = aggregateRepository.save(cmd);
        // place other business logic here. Mainly one which required coordination with other services/resources
        return orderWithEvents.getAggregate();
    }*/
}
