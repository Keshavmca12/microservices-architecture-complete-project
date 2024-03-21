package org.pg.cartservice.application.core.domain;

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
