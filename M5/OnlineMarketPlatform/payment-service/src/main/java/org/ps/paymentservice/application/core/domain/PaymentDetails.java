package org.ps.paymentservice.application.core.domain;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetails {
    private String paymentMode;
    private String accountno;
}
