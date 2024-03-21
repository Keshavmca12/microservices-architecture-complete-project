package org.ps.paymentservice.application.core.domain.enums;

public enum PaymentStatusEnum {
    INITIATED,
    PAYMENT_APPROVED,
    PAYMENT_REJECTED,
    PENDING;

    private PaymentStatusEnum() {
    }
}