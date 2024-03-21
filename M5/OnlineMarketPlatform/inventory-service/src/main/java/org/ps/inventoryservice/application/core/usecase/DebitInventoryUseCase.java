package org.ps.inventoryservice.application.core.usecase;


import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.in.DebitInventoryInputPort;
import org.ps.inventoryservice.application.ports.in.FindInventoryBySkuIdInputPort;
import org.ps.inventoryservice.application.ports.out.SendDebitInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.SendFailedInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.UpdateInventoryOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DebitInventoryUseCase implements DebitInventoryInputPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebitInventoryUseCase.class);

    private final FindInventoryBySkuIdInputPort findInventoryBySkuIdInputPort;
    private final UpdateInventoryOutputPort updateInventoryOutputPort;
    private final SendDebitInventoryOutputPort sendDebitInventoryOutputPort;
    private final SendFailedInventoryOutputPort sendFailedInventoryOutputPort;

    public DebitInventoryUseCase(final FindInventoryBySkuIdInputPort findInventoryBySkuIdInputPort,
                                 final UpdateInventoryOutputPort updateInventoryOutputPort,
                                 final SendDebitInventoryOutputPort sendDebitInventoryOutputPort,
                                 final SendFailedInventoryOutputPort sendFailedInventoryOutputPort) {

        this.findInventoryBySkuIdInputPort = findInventoryBySkuIdInputPort;
        this.updateInventoryOutputPort = updateInventoryOutputPort;
        this.sendDebitInventoryOutputPort = sendDebitInventoryOutputPort;
        this.sendFailedInventoryOutputPort = sendFailedInventoryOutputPort;
    }

    @Override
    public void execute(final Order order) {
        try {
            order.getItems().stream().forEach(item -> {
                Inventory inventory = this.findInventoryBySkuIdInputPort.execute(item.getSku());
                this.checkQuantityInInventory(inventory, item.getUnit());
                inventory.debitQuantity(item.getUnit());
                this.updateInventoryOutputPort.execute(inventory);
            });
            this.sendDebitInventoryOutputPort.execute(order, OrderEventEnum.UPDATED_INVENTORY);
        } catch (final Exception ex) {
            LOGGER.error("DebitInventoryUseCase - error [{}]", ex.getMessage());
            this.sendFailedInventoryOutputPort.execute(order, OrderEventEnum.ROLLBACK_INVENTORY);
        }
    }

    private void checkQuantityInInventory(final Inventory inventory, final int saleQuantity) {
        if (inventory.getQuantity() < saleQuantity) throw new RuntimeException("Out of stock");
    }
}
