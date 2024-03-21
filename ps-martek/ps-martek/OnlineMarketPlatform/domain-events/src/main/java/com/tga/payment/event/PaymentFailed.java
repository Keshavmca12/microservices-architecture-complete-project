package com.tga.payment.event;

import com.tga.eventsource.event.Event;
import com.tga.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentFailed implements PaymentEvent {
    Payment payment;
}
