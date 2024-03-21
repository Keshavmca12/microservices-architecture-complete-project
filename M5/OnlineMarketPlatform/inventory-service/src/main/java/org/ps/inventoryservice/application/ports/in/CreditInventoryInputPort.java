package org.ps.inventoryservice.application.ports.in;


import org.ps.inventoryservice.application.core.domain.Order;

public interface CreditInventoryInputPort {

    void execute(Order order);
}
