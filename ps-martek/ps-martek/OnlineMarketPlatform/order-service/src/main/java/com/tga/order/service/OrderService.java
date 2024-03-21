package com.tga.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

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

    private String orderEventsTopic;

    private String orderGroupId;

    private final KafkaTemplate<String, String> kafkaTemplate; // Adjust the types according to your use case
    @Autowired
    public OrderService(
            AggregateRepository<Order, OrderCommand> aggregateRepository,
            OrderRepository orderRepository,
            KafkaPublisher publisher,
            @Value("${kafka.topic.order-events}") String orderEventsTopic,
            @Value("${kafka.group-id.order}") String orderGroupId,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.aggregateRepository = aggregateRepository;
        this.orderRepository = orderRepository;
        this.publisher = publisher;
        this.orderEventsTopic = orderEventsTopic;
        this.orderGroupId = orderGroupId;
        this.kafkaTemplate = kafkaTemplate;
    }

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

        String orderJson = serializeOrder(order); // Convert order object to JSON or use your preferred serialization method
        kafkaTemplate.send(orderEventsTopic, orderGroupId, orderJson);

        return order;
    }

    private String serializeOrder(Order order) {
         ObjectMapper objectMapper = new ObjectMapper();
         try {
             return objectMapper.writeValueAsString(order);
         } catch (JsonProcessingException e) {
             throw new RuntimeException("Error serializing order to JSON", e);
         }
    }

}
