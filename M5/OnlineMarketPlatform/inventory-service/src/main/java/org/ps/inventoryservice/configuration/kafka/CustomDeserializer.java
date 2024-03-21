package org.ps.inventoryservice.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.ps.inventoryservice.adapters.out.message.OrderMessage;

public class CustomDeserializer implements Deserializer<OrderMessage> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), OrderMessage.class);
        } catch (final Exception e) {
            throw new SerializationException("Error when deserializing byte[] to SaleMessage");
        }
    }

}
