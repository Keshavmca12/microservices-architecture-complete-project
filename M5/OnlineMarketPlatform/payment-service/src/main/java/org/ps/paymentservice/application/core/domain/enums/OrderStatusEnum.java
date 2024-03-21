package org.ps.paymentservice.application.core.domain.enums;

import java.util.Arrays;

public enum OrderStatusEnum {

    PENDING(1),
    FINALIZED(2),
    CANCELED(3);

    private final Integer statusId;

    OrderStatusEnum(final Integer statusId) {
        this.statusId = statusId;
    }

    public static OrderStatusEnum getSaleStatusByStatusId(final Integer statusId) {
        if (statusId == null) return null;

        return Arrays.stream(OrderStatusEnum.values())
                .filter(status -> status.getStatusId() == statusId).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid statusId: " + statusId));
    }

    public Integer getStatusId() {
        return statusId;
    }
}
