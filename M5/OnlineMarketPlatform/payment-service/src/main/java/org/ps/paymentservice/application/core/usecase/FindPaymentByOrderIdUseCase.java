package org.ps.paymentservice.application.core.usecase;

import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.ports.in.FindPaymentByOrderIdInputPort;
import org.ps.paymentservice.application.ports.out.FindPaymentByOrderIdOutputPort;
import org.springframework.stereotype.Component;

@Component
public class FindPaymentByOrderIdUseCase implements FindPaymentByOrderIdInputPort {
    final FindPaymentByOrderIdOutputPort findPaymentByOrderIdOutputPort;

    public FindPaymentByOrderIdUseCase(FindPaymentByOrderIdOutputPort findPaymentByOrderIdOutputPort) {
        this.findPaymentByOrderIdOutputPort = findPaymentByOrderIdOutputPort;
    }

    @Override
    public Payment execute(String orderId) {
        return findPaymentByOrderIdOutputPort.execute(orderId);
    }
}
