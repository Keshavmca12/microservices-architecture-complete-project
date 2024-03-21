package com.tga.payment.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Data
@ToString
public class Payment {
    private String id;
    private String customerId;
    private String orderId;
    private double totalAmount;
    private PaymentTxnStatus status;
    private List<Instruction> instructions;
}
