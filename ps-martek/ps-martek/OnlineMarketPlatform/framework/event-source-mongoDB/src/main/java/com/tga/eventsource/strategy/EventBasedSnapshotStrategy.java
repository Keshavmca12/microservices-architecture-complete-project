package com.tga.eventsource.strategy;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.model.AggregateSnapshot;
import com.tga.eventsource.repo.AggregateSnapshotRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
public class EventBasedSnapshotStrategy implements SnapshotStrategy {

    private AggregateSnapshotRepository aggregateSnapshotRepository;
    private List<String> eventsForSnapshot;

    private EventBasedSnapshotStrategy(){}

    public EventBasedSnapshotStrategy(AggregateSnapshotRepository aggregateSnapshotRepository, Class<? extends Event>... eventsForSnapshot) {
        this.aggregateSnapshotRepository = aggregateSnapshotRepository;
        this.eventsForSnapshot = Arrays.asList(eventsForSnapshot).stream()
                .map(e -> e.getName()).collect(Collectors.toList());
        log.info("Registered Events for Aggregate Snapshotting: {}", eventsForSnapshot);
    }


    @Override
    public AggregateSnapshot getLatestSnapshot(String entityId) {

        List<AggregateSnapshot> snapshots = aggregateSnapshotRepository.findByEntityId(entityId);

        if (snapshots.isEmpty())
            return null;
        else
            return snapshots.get(snapshots.size() - 1);
    }

    @Override
    public AggregateSnapshot possibleSnapshot(@NotNull Aggregate aggregate, @NotNull List<Event> events, Long lastProcessedEventId) {

        AggregateSnapshot snapshot = null;

        for (Event event: events) {
            if (eventsForSnapshot.contains(event.getClass().getName())) {
                try {
                    snapshot = new AggregateSnapshot(aggregate, event, lastProcessedEventId);
                    snapshot = aggregateSnapshotRepository.save(snapshot);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Unable to take snapshot for the event:" + event.getClass().getName(), e);
                }
            }
        }
        return snapshot;

    }
}
