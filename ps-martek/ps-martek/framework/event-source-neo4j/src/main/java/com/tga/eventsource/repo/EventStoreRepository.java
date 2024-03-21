package com.tga.eventsource.repo;

import com.tga.eventsource.model.EventStore;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface EventStoreRepository extends Neo4jRepository<EventStore, Long> {

    List<EventStore> findByEntityId(String entityId);

    List<EventStore> findByEntityIdAndIdGreaterThan(String entityId, Long eventId);

}
