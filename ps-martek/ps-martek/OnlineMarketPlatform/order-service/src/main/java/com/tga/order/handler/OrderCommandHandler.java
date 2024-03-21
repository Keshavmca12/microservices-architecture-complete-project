package com.tga.order.handler;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.command.Command;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.handler.AbstractCommandHandler;
import com.tga.order.model.Order;
import com.tga.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderCommandHandler extends AbstractCommandHandler {

    @Autowired
    private OrderService orderService;

    @Override
    public List<Event> handleCommand(Command command) {
        //call service class command handler methods from here
        return null;
    }

    @Override
    public Class<? extends Aggregate> aggregateClassName() {
        return Order.class;
    }
}
