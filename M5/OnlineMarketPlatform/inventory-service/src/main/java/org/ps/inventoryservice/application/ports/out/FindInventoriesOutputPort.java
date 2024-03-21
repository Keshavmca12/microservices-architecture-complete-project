package org.ps.inventoryservice.application.ports.out;



import org.ps.inventoryservice.application.core.domain.Inventory;

import java.util.List;
import java.util.Optional;

public interface FindInventoriesOutputPort {

    List<Inventory> execute();
}
