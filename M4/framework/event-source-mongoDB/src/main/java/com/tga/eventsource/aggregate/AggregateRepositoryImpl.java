package com.tga.eventsource.aggregate;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.command.Command;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.model.AggregateSnapshot;
import com.tga.eventsource.model.EventStore;
import com.tga.eventsource.repo.EventStoreRepository;
import com.tga.eventsource.strategy.SnapshotStrategy;
import com.tga.eventsource.util.AggregateWithEvents;
import com.tga.eventsource.util.Aggregates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AggregateRepositoryImpl<T extends Aggregate, S extends Command> implements AggregateRepository<T, S> {

    @Autowired
    private EventStoreRepository esRepo;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SnapshotStrategy snapshotStrategy;

    private Class<T> clazz;


    /**
     *
     * @param clazz
     */
    public AggregateRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     *
     * @param entityId
     * @return
     */
    @Override
    public Optional<T> loadAggregateByEntityId(String entityId) {
       // return repository.findById(id);
        AggregateSnapshot snapshot = snapshotStrategy.getLatestSnapshot(entityId);
        T aggregate = null;
        if (snapshot == null) {
            aggregate = loadAggregateUsingEvents(entityId);
        } else {
            aggregate = loadAggregateUsingSnapshot(snapshot);
        }
        return Optional.ofNullable(aggregate);
    }

    /**
     *
     * @param cmd
     * @return
     */
    @Override
    public AggregateWithEvents<T> save(S cmd) {

        T aggregate = loadAggregateByEntityId(cmd.getId()).get();
        List<Event> events = aggregate.processCommand(cmd);

        try {
            //aggregate = repository.save(aggregate);
            Long lastProcessedEventId = 0l;
            for (Event event : events) {
                EventStore es = new EventStore();
                es.setEventType(event.getClass().getCanonicalName());
                es.setEntityType(clazz.getCanonicalName());
                es.setEntityId(aggregate.getId());
                es.setEventData(new ObjectMapper().writeValueAsString(event));
                log.info("Storing Event type : {}", es.getEventType());
                lastProcessedEventId = esRepo.save(es).getId();
            }
            snapshotStrategy.possibleSnapshot(aggregate, events, lastProcessedEventId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while storing the events to event store");
        }
        return new AggregateWithEvents<T>(aggregate, events);
    }

    /**
     *
     * @param entityId
     * @return
     */
    @NotNull
    private T loadAggregateUsingEvents(String entityId) {
        T aggregate = null;
        try {
            aggregate = clazz.getConstructor().newInstance();
            if(entityId != null) {
                List<EventStore> events = esRepo.findByEntityId(entityId);
                aggregate = Aggregates.applyEvents(aggregate, events);
            }
            aggregate.setId(entityId);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return aggregate;
    }

    /**
     *
     * @param snapshot
     * @return
     */
    @NotNull
    private T loadAggregateUsingSnapshot(@NotNull AggregateSnapshot snapshot) {
        T aggregate = null;
        try {
            List<EventStore> events = esRepo.findByEntityIdAndIdGreaterThan(snapshot.getEntityId(),
                    snapshot.getProcessedEventId());

            aggregate = (T) new ObjectMapper().readValue(snapshot.getEventData(),
                    Class.forName(snapshot.getEntityType()));

            aggregate = Aggregates.applyEvents(aggregate, events);
            aggregate.setId(snapshot.getEntityId());
        } catch (ClassNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return aggregate;
    }

}
