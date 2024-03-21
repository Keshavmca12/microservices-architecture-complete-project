package com.tga.eventsource.repo;

import com.tga.eventsource.model.AggregateSnapshot;
import com.tga.eventsource.model.EventStore;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AggregateSnapshotRepository extends Neo4jRepository<AggregateSnapshot, Long> {

    List<AggregateSnapshot> findByEntityId(String entityId);

}
