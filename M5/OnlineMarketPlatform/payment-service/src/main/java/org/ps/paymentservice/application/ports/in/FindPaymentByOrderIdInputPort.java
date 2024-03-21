package org.ps.paymentservice.application.ports.in;

import org.ps.paymentservice.application.core.domain.Payment;

public interface FindPaymentByOrderIdInputPort {
    Payment execute(String orderId);

}
