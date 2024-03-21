package org.ps.inventoryservice.application.ports.in;

import org.ps.inventoryservice.application.core.domain.Inventory;

import java.util.List;

public interface FindInventoriesInputPort {
    List<Inventory> execute();
}

