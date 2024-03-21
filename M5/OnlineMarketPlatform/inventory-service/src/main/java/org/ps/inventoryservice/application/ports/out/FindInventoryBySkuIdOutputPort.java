package org.ps.inventoryservice.application.ports.out;



import org.ps.inventoryservice.application.core.domain.Inventory;

import java.util.Optional;

public interface FindInventoryBySkuIdOutputPort {

    Optional<Inventory> execute(String skuid);
}
