package com.tga.eventsource.util;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import lombok.Getter;

import java.util.List;

@Getter
public class AggregateWithEvents<T extends Aggregate> {

    private List<Event> events;
    private T aggregate;

    public AggregateWithEvents(T aggregate, List<Event> events){
        this.aggregate =  aggregate;
        this.events = events;
    }

}
