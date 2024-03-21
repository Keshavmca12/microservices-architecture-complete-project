package org.ps.paymentservice.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.adapters.out.message.OrderMessage;
import org.ps.paymentservice.application.core.domain.Order;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;
import org.ps.paymentservice.application.ports.out.SendFailedPaymentOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendFailedPaymentAdapter implements SendFailedPaymentOutputPort {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void execute(final Order order, final OrderEventEnum orderEventEnum) {
        log.info("SendFailedPaymentAdapter - Send failed payment");

        String key = UUID.randomUUID().toString();
        OrderMessage orderMessage = new OrderMessage(order, orderEventEnum);
        this.kafkaTemplate.send("tp-inventory", key, orderMessage);
    }
}

