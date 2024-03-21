package com.tga.order.handler;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.handler.AbstractEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderEventHandler extends AbstractEventHandler {

    @Override
    public List<Event> handleEvents(Aggregate aggregate, Event event) {
        try{
           return aggregate.applyEvent(event);
        }catch (Exception exp){
            log.error("Error while handling events for {}",event.getClass().getSimpleName());
            throw new RuntimeException(exp);
        }
    }
}
