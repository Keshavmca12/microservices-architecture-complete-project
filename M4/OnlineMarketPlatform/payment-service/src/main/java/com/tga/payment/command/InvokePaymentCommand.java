package com.tga.payment.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class InvokePaymentCommand extends PaymentCommand{
    private long orderId;

    private String paymentMode;

    private String referenceNumber;

    private Instant paymentDate;

    private String paymentStatus;

    private Double amount;
}
