package org.ps.inventoryservice.application.ports.out;

import org.ps.inventoryservice.application.core.domain.Order;
import org.ps.inventoryservice.application.core.domain.enums.OrderEventEnum;

public interface SendFailedInventoryOutputPort {

    void execute(Order order, OrderEventEnum orderEventEnum);

}
