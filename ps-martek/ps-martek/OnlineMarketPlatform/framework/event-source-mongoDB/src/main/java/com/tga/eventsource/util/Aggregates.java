package com.tga.eventsource.util;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.model.EventStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

public class Aggregates {
    public static <T extends Aggregate> T applyEvents(T aggregate, List<EventStore> events) {
        try {
            for (EventStore es : events) {
                //  Event event = new EventPayload().getPayload(es.getEventData());
                Event event = (Event) new ObjectMapper().readValue(es.getEventData(), Class.forName(es.getEventType()));
                aggregate.applyEvent(event);
                aggregate.setId(es.getEntityId());
            }
        }catch (Exception exp){
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
        return aggregate;
    }

}

class EventPayload {

    public Event getPayload(String data) throws JsonProcessingException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNodeRoot = objectMapper.readTree(data);
            String payload = jsonNodeRoot.get("payload").toString();
            JsonNode metaNode = jsonNodeRoot.get("meta");
            return (Event) objectMapper.readValue(payload, Class.forName(metaNode.get("eventClass").asText()));

        }catch (Exception exp){
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
    }

    public  void test() {
        try{
            String msg = "{ \"meta\" : { \"evenType\": \"OrderCreatedEvent\", \"eventClass\": \"com.bsd.order.event.OrderCreatedEvent\"}, \"payload\": { \"orderId\": 123 }}";
            Event event = new EventPayload().getPayload(msg);
           // Event event = new EventPayload().getPayload(msg, com.bsd.order.event.OrderCreatedEvent.class);
            System.out.println(event.getClass().getSimpleName());
        }catch (Exception exp){
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
    }

}


//{
//
//    "meta" : {
//        "evenType": "ItemAddedEvent" //ItemRemovedEvent, OrderCreatedEvent
//    },
//    "payload": {
//        item //ItemAddedEvent
//        orderId //OrderCreatedEvent
//
//    }
//}
