package org.ps.inventoryservice.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.ps.inventoryservice.adapters.out.message.OrderMessage;

public class CustomSerializer implements Serializer<OrderMessage> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, OrderMessage saleMessage) {
        try {
            if (saleMessage == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(saleMessage);
        } catch (final Exception e) {
            throw new SerializationException("Error when serializing SaleMessage to byte[]");
        }
    }

}
