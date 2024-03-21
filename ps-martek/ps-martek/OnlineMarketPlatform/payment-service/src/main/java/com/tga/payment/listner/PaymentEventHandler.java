package com.tga.payment.listner;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.handler.AbstractEventHandler;
import com.tga.order.model.Order;
import com.tga.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PaymentEventHandler extends AbstractEventHandler {
    @Autowired
    private PaymentService productService;

    @Override
    public List<Event> handleEvents(Aggregate aggregate, Event event) {
        try{
            log.info("Got message <{}>",event);

        Long savedId = productService.doPayment((Order)event);
        log.info("Updated payment <{}>", savedId);
           return aggregate.applyEvent(event);
        }catch (Exception exp){
            log.error("Error while handling events for {}",event.getClass().getSimpleName());
            throw new RuntimeException(exp);
        }
    }
}
