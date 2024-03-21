package com.tga.eventsource.aggregate;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.command.Command;
import com.tga.eventsource.util.AggregateWithEvents;

import java.util.Optional;

public interface AggregateRepository<T extends Aggregate, S extends Command>{

    Optional<T> loadAggregateByEntityId(String entityId);

    AggregateWithEvents<T> save(S cmd);

}
