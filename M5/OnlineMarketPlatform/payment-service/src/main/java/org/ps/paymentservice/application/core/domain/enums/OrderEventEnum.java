package org.ps.paymentservice.application.core.domain.enums;

public enum OrderEventEnum {

    CREATED_ORDER,
    UPDATED_INVENTORY,
    VALIDATED_PAYMENT,
    ROLLBACK_INVENTORY,
    FAILED_PAYMENT

}
