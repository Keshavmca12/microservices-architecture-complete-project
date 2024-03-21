package com.tga.pos.customerservice.server;

import com.fasterxml.jackson.databind.*;
import com.tga.customer.event.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.serialization.*;


public class CustomDeserializer implements Deserializer<CustomerAddedEvent> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CustomerAddedEvent deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), CustomerAddedEvent.class);
        } catch (final Exception e) {
            throw new SerializationException("Error when deserializing byte[] to SaleMessage");
        }
    }

}
