package org.ps.inventoryservice.application.ports.in;

import org.ps.inventoryservice.application.core.domain.Order;

public interface DebitInventoryInputPort {

    void execute(Order order);
}
