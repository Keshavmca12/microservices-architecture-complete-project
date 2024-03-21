package com.tga.eventsource.model;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import javax.persistence.GeneratedValue;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateSnapshot {

    @Id
    @GeneratedValue
    private Long id;
    private String eventType;
    private String entityType;
    private String entityId;
    private String eventData;
    private Long processedEventId;
    private Date lastSnapshotTime;
    private Long version;

    public AggregateSnapshot(Aggregate aggregate, Event event, Long lastProcessedEventId) throws JsonProcessingException {
        this.eventType = event.getClass().getName();
        this.entityType = aggregate.getClass().getName();
        this.entityId =  aggregate.getId();
        this.eventData =  new ObjectMapper().writeValueAsString(aggregate);
        this.lastSnapshotTime = new Date();
        this.processedEventId = lastProcessedEventId;
       // this.version = this.version==null?0:this.version+1;
    }

}
