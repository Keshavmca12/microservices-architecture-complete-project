package com.tga.eventsource.util;

import com.tga.eventsource.event.Event;

import java.util.Arrays;
import java.util.List;

public class EventUtil {

    public static List<Event> events(Event ... events) {
        return Arrays.asList(events);
    }

}
