package org.ps.inventoryservice.application.core.usecase;


import lombok.AllArgsConstructor;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;
import org.ps.inventoryservice.application.ports.in.CreditInventoryInputPort;
import org.ps.inventoryservice.application.ports.in.FindInventoryBySkuIdInputPort;
import org.ps.inventoryservice.application.ports.in.SaveInventoryInputPort;
import org.ps.inventoryservice.application.ports.out.SaveInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.SendCreditInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.UpdateInventoryOutputPort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveInventoryUseCase implements SaveInventoryInputPort {

    private final SaveInventoryOutputPort saveInventoryOutputPort;

    @Override
    public void execute(final Inventory inventory) {
        this.saveInventoryOutputPort.execute(inventory);
    }
}
