package org.ps.paymentservice.application.core.domain;

import lombok.*;
import org.ps.paymentservice.application.core.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String id;
    private String userId;
    private String orderId;
    private BigDecimal value;
    private PaymentDetails paymentDetails;
    private PaymentStatusEnum paymentStatus;
}
