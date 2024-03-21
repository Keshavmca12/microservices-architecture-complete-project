package org.ps.inventoryservice.application.ports.out;


import org.ps.inventoryservice.application.core.domain.Inventory;

public interface UpdateInventoryOutputPort {

    void execute(Inventory inventory);
}
