package com.tga.eventsource.command;

import com.tga.eventsource.event.Event;
import lombok.Data;

@Data
public abstract class Command implements Event {
    private String id;
}
