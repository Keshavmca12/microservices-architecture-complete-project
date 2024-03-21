package org.ps.paymentservice.adapters.out;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.adapters.out.repository.PaymentRepository;
import org.ps.paymentservice.adapters.out.repository.entity.PaymentEntity;
import org.ps.paymentservice.adapters.out.repository.mapper.ModelMapperUtil;
import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.ports.out.SavePaymentOutputPort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SavePaymentAdapter implements SavePaymentOutputPort {

    private final PaymentRepository paymentRepository;


    @Override
    public void execute(final Payment payment) {
        final PaymentEntity paymentEntity = ModelMapperUtil.map(payment, PaymentEntity.class);
        paymentEntity.setId(UUID.randomUUID().toString());
        paymentEntity.getPaymentDetails().setId(UUID.randomUUID().toString());
        paymentEntity.getPaymentDetails().setPayment(paymentEntity);
        paymentEntity.setCreatedAt(Instant.now());
        this.paymentRepository.save(paymentEntity);
    }
}
