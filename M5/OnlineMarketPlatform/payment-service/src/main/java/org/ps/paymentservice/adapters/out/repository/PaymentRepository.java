package org.ps.paymentservice.adapters.out.repository;


import org.ps.paymentservice.adapters.out.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
    PaymentEntity findByOrderId(String orderId);
}
