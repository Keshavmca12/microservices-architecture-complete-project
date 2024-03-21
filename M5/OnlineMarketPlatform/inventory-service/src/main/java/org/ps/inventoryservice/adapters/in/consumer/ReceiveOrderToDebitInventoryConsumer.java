package org.ps.inventoryservice.adapters.in.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.inventoryservice.adapters.out.message.OrderMessage;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.in.DebitInventoryInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveOrderToDebitInventoryConsumer {

    private final DebitInventoryInputPort debitInventoryInputPort;

    @KafkaListener(topics = "tp-inventory", groupId = "inventory-debit")
    public void receive(final OrderMessage orderMessage) {
        if (OrderEventEnum.CREATED_ORDER.equals(orderMessage.getOrderEventEnum())) {
            log.info("ReceiveSaleToDebitInventoryConsumer - Start inventory Deduct");

            this.debitInventoryInputPort.execute(orderMessage.getOrder());
        }
    }
}
