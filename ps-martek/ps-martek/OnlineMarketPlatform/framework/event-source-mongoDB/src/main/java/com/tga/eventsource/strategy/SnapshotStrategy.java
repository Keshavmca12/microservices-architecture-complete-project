package com.tga.eventsource.strategy;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.model.AggregateSnapshot;

import java.util.List;


public interface SnapshotStrategy {

    AggregateSnapshot getLatestSnapshot(String entityId);

    AggregateSnapshot  possibleSnapshot(Aggregate aggregate, List<Event> events, Long lastProcessedEventId);

}
