package org.ps.inventoryservice.application.core.usecase;


import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.in.CreditInventoryInputPort;
import org.ps.inventoryservice.application.ports.in.FindInventoryBySkuIdInputPort;
import org.ps.inventoryservice.application.ports.out.SendCreditInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.UpdateInventoryOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CreditInventoryUseCase implements CreditInventoryInputPort {

    private final FindInventoryBySkuIdInputPort findInventoryBySkuIdInputPort;

    private final UpdateInventoryOutputPort updateInventoryOutputPort;

    private final SendCreditInventoryOutputPort sendCreditInventoryOutputPort;

    public CreditInventoryUseCase(final FindInventoryBySkuIdInputPort findInventoryBySkuIdInputPort,
                                  final UpdateInventoryOutputPort updateInventoryOutputPort,
                                  final SendCreditInventoryOutputPort sendCreditInventoryOutputPort) {

        this.findInventoryBySkuIdInputPort = findInventoryBySkuIdInputPort;
        this.updateInventoryOutputPort = updateInventoryOutputPort;
        this.sendCreditInventoryOutputPort = sendCreditInventoryOutputPort;
    }

    @Override
    public void execute(final Order order) {
        order.getItems().stream().forEach(item -> {
            Inventory inventory = this.findInventoryBySkuIdInputPort.execute(item.getSku());
            inventory.creditQuantity(item.getUnit());
            this.updateInventoryOutputPort.execute(inventory);
        });
        this.sendCreditInventoryOutputPort.execute(order, OrderEventEnum.ROLLBACK_INVENTORY);
    }
}
