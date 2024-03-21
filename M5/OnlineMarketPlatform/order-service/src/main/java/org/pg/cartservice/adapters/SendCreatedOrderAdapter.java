package org.pg.cartservice.adapters;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pg.cartservice.adapters.out.message.OrderMessage;
import org.pg.cartservice.application.core.domain.Order;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;
import org.pg.cartservice.application.port.out.SendCreateOrderOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendCreatedOrderAdapter implements SendCreateOrderOutputPort {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void execute(final Order order, final OrderEventEnum orderEventEnum) {
        log.info("SendCreatedOrderAdapter - Send order kafka event: {}", orderEventEnum);
        String key = UUID.randomUUID().toString();
        final OrderMessage orderMessage = OrderMessage.builder().order(order).orderEventEnum(orderEventEnum).build();
        this.kafkaTemplate.send("tp-inventory", key, orderMessage);
    }
}
