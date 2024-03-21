package org.pg.cartservice.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pg.cartservice.adapters.in.controller.request.OrderRequest;
import org.pg.cartservice.adapters.out.repository.mapper.ModelMapperUtil;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.port.in.CreateOrderInputPort;
import org.pg.cartservice.application.port.in.FindOrderByIdInputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderInputPort createOrderInputPort;

    private final FindOrderByIdInputPort findOrderByIdInputPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@Valid @RequestBody final OrderRequest orderRequest) {
        log.info("OrderController - Creating order [{}]", orderRequest);
        final Order order = ModelMapperUtil.map(orderRequest, Order.class);
        this.createOrderInputPort.execute(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId")
                                              @Valid String orderId) {
        log.info("OrderController - Get order for id [{}]", orderId);
        return ResponseEntity.of(Optional.of(this.findOrderByIdInputPort.execute(orderId)));
    }
}
