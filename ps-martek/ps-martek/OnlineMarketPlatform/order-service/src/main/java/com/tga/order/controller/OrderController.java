package com.tga.order.controller;


import com.tga.order.command.CreateOrderCommand;
import com.tga.order.model.Order;
import com.tga.order.model.OrderRestModel;
import com.tga.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody OrderRestModel orderRestModel) {

        CreateOrderCommand createOrderCommand= new CreateOrderCommand();
        createOrderCommand.setAddressId(orderRestModel.getAddressId());
        createOrderCommand.setCustomerId(orderRestModel.getUserId());
        createOrderCommand.setItems(orderRestModel.getItemList());
        Order order = orderService.createOrder(createOrderCommand);

        return order.getId();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@RequestParam("id") String orderId){
        log.info("REST API Controller - getOrder: {}", orderId);
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

}
