package org.ps.inventoryservice.application.ports.in;


import org.ps.inventoryservice.application.core.domain.Inventory;

public interface FindInventoryBySkuIdInputPort {

    Inventory execute(String skuId);
}
