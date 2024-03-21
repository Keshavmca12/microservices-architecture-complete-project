package org.ps.paymentservice.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.adapters.out.message.OrderMessage;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;
import org.ps.paymentservice.application.ports.in.OrderPaymentInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveOrderToPaymentConsumer {

    private final OrderPaymentInputPort orderPaymentInputPort;

    @KafkaListener(topics = "tp-payment", groupId = "payment")
    public void execute(final OrderMessage orderMessage) {
        if (OrderEventEnum.UPDATED_INVENTORY.equals(orderMessage.getOrderEventEnum())) {
            log.info("ReceiveOrderToPaymentConsumer - Start payment");
            this.orderPaymentInputPort.execute(orderMessage.getOrder());
        }
    }

}
