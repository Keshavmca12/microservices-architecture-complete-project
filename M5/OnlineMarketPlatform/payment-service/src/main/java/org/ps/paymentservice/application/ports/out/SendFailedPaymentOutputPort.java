package org.ps.paymentservice.application.ports.out;

import org.ps.paymentservice.application.core.domain.Order;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;

public interface SendFailedPaymentOutputPort {


    void execute(Order order, OrderEventEnum orderEventEnum);
}
