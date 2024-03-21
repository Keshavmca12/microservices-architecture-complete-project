package org.ps.paymentservice.application.ports.in;


import org.ps.paymentservice.application.core.domain.Order;

public interface OrderPaymentInputPort {

    void execute(Order order);
}
