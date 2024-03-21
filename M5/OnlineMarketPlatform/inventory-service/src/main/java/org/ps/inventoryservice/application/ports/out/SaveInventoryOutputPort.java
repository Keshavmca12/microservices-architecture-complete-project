package org.ps.inventoryservice.application.ports.out;


import org.ps.inventoryservice.application.core.domain.Inventory;

public interface SaveInventoryOutputPort {

    void execute(Inventory inventory);
}
