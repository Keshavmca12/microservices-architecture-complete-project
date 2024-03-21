package org.ps.inventoryservice.application.core.usecase;

import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.in.FindInventoryBySkuIdInputPort;
import org.ps.inventoryservice.application.ports.out.FindInventoryBySkuIdOutputPort;
import org.springframework.stereotype.Component;

@Component
public class FindInventoryBySkuIdUseCase implements FindInventoryBySkuIdInputPort {

    private final FindInventoryBySkuIdOutputPort findInventoryBySkuIdOutputPort;

    public FindInventoryBySkuIdUseCase(final FindInventoryBySkuIdOutputPort findInventoryBySkuIdOutputPort) {
        this.findInventoryBySkuIdOutputPort = findInventoryBySkuIdOutputPort;
    }

    @Override
    public Inventory execute(final String skuId) {
        return this.findInventoryBySkuIdOutputPort.execute(skuId)
                .orElseThrow(() -> new RuntimeException("No Inventory Found"));
    }
}
