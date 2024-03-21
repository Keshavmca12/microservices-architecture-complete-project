package org.ps.paymentservice.application.ports.out;

import org.ps.paymentservice.application.core.domain.Payment;

public interface FindPaymentByOrderIdOutputPort {
    Payment execute(String id);
}
