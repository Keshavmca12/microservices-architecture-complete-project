package com.tga.eventsource.model;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
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
