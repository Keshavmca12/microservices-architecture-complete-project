package org.ps.inventoryservice.application.ports.in;


import org.ps.inventoryservice.application.core.domain.Inventory;

public interface SaveInventoryInputPort {

    void execute(Inventory inventory);
}
