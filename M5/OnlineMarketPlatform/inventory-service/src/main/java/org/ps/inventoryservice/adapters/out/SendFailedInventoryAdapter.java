package org.ps.inventoryservice.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.inventoryservice.adapters.out.message.OrderMessage;
import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.out.SendFailedInventoryOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendFailedInventoryAdapter implements SendFailedInventoryOutputPort {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void execute(final Order order, final OrderEventEnum orderEventEnum) {
        log.info("SendFailedInventoryAdapter - Send failed inventory");

        String key = UUID.randomUUID().toString();
        final OrderMessage orderMessage = new OrderMessage(order, orderEventEnum);
        this.kafkaTemplate.send("tp-order", key, orderMessage);
    }
}
