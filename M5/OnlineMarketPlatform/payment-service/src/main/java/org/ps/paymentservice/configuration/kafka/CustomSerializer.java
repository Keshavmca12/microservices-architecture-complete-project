package org.ps.paymentservice.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.ps.paymentservice.adapters.out.message.OrderMessage;

public class CustomSerializer implements Serializer<OrderMessage> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String o, OrderMessage orderMessage) {
        try {
            if (orderMessage == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(orderMessage);
        } catch (final Exception e) {
            throw new SerializationException("Error when serializing OrderMessage to byte[]");
        }
    }

}
