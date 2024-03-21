package com.tga.eventsource.repo;

import com.tga.eventsource.model.AggregateSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AggregateSnapshotRepository extends MongoRepository<AggregateSnapshot, Long> {

    List<AggregateSnapshot> findByEntityId(String entityId);

}
