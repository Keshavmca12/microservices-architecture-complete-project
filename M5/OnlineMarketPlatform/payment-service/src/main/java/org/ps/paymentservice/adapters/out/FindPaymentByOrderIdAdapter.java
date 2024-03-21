package org.ps.paymentservice.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.adapters.out.repository.PaymentRepository;
import org.ps.paymentservice.adapters.out.repository.mapper.ModelMapperUtil;
import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.ports.out.FindPaymentByOrderIdOutputPort;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindPaymentByOrderIdAdapter implements FindPaymentByOrderIdOutputPort {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment execute(String orderId) {
        return ModelMapperUtil.map(paymentRepository.findByOrderId(orderId), Payment.class);
    }
}
