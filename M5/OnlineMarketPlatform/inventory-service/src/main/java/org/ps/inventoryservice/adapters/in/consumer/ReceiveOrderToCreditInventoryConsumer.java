package org.ps.inventoryservice.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.inventoryservice.adapters.out.message.OrderMessage;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.in.CreditInventoryInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveOrderToCreditInventoryConsumer {

    private final CreditInventoryInputPort creditInventoryInputPort;

    @KafkaListener(topics = "tp-inventory", groupId = "inventory-credit")
    public void execute(final OrderMessage orderMessage) {
        if (OrderEventEnum.FAILED_PAYMENT.equals(orderMessage.getOrderEventEnum())) {
            log.info("ReceiveOrderToCreditInventoryConsumer - Start credit inventory ");

            this.creditInventoryInputPort.execute(orderMessage.getOrder());
        }
    }

}
