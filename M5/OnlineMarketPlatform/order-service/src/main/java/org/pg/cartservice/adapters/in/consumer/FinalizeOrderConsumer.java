package org.pg.cartservice.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pg.cartservice.adapters.out.message.OrderMessage;
import org.pg.cartservice.application.core.domain.enums.OrderEventEnum;
import org.pg.cartservice.application.port.in.FinaliseOrderInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinalizeOrderConsumer {

    private final FinaliseOrderInputPort finaliseOrderInputPort;


    @KafkaListener(topics = "tp-order", groupId = "order-finalize")
    public void execute(final OrderMessage orderMessage) {
        if (OrderEventEnum.VALIDATED_PAYMENT.equals(orderMessage.getOrderEventEnum())) {
            log.info("FinaliseOrderInputPort - finalise the order");
            this.finaliseOrderInputPort.execute(orderMessage.getOrder());
        }
    }

}
