package com.tga.payment.event;

import com.tga.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentCreatedEvent implements PaymentEvent {

    private Payment payment;
}
