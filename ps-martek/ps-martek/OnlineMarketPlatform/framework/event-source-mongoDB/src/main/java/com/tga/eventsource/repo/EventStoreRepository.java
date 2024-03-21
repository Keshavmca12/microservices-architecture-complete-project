package com.tga.eventsource.repo;

import com.tga.eventsource.model.EventStore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventStore, Long> {

    List<EventStore> findByEntityId(String entityId);

    List<EventStore> findByEntityIdAndIdGreaterThan(String entityId, Long eventId);

}
