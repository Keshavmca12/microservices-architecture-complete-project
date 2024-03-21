package org.ps.paymentservice.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.adapters.out.message.OrderMessage;
import org.ps.paymentservice.application.core.domain.Order;
import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;
import org.ps.paymentservice.application.ports.out.SendValidatedPaymentOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendValidatedPaymentAdapter implements SendValidatedPaymentOutputPort {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    @Override
    public void execute(final Order order, final OrderEventEnum orderEventEnum) {
        log.info("SendValidatedPaymentAdapter - Payment Validated Event: [{}]", orderEventEnum);

        var key = UUID.randomUUID().toString();
        OrderMessage orderMessage = new OrderMessage(order, orderEventEnum);
        this.kafkaTemplate.send("tp-order", key, orderMessage);
    }
}
