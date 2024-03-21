package com.tga.pos.customerservice.server;

import com.fasterxml.jackson.databind.*;
import com.tga.customer.event.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.serialization.*;


public class CustomSerializer implements Serializer<CustomerAddedEvent> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, CustomerAddedEvent customerMessage) {
        try {
            if (customerMessage == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(customerMessage);
        } catch (final Exception e) {
            throw new SerializationException("Error when serializing SaleMessage to byte[]");
        }
    }

}
