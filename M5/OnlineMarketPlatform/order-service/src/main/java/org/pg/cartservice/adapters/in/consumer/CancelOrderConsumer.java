package org.pg.cartservice.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pg.cartservice.adapters.out.message.OrderMessage;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;
import org.pg.cartservice.application.port.in.CancelOrderInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelOrderConsumer {

    private final CancelOrderInputPort cancelOrderInputPort;

    @KafkaListener(topics = "tp-order", groupId = "order-cancel")
    public void execute(final OrderMessage orderMessage) {
        if (OrderEventEnum.ROLLBACK_INVENTORY.equals(orderMessage.getOrderEventEnum())) {
            log.info("CancelOrderConsumer - Canceling Order");
            this.cancelOrderInputPort.execute(orderMessage.getOrder());

        }
    }
}


