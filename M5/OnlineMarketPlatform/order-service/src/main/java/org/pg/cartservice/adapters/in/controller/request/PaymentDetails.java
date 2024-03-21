package org.pg.cartservice.adapters.in.controller.request;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentDetails {
    private String paymentMode;
    private String accountno;
}
