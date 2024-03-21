package com.tga.eventsource.event;

import com.tga.eventsource.command.Aggregate;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class NoActionEvent implements Event{

    private String aggregateClassType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.CLASS,
            include = JsonTypeInfo.As.PROPERTY,
            property = "aggregateClassType")
    private Aggregate aggregate;

    public NoActionEvent(){}

    public NoActionEvent(Aggregate aggregate){
        this.aggregate = aggregate;
        this.aggregateClassType = aggregate.getClass().getCanonicalName();
    }
}
