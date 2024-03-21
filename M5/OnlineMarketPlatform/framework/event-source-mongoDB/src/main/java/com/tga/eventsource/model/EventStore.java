package com.tga.eventsource.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Data
public class EventStore {

    @Id
    @GeneratedValue
    private Long id;
    private String eventType;
    private String entityType;
    private String entityId;
    private String eventData;

}
