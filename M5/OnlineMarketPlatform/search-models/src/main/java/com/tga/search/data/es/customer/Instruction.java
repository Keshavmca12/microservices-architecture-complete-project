package com.tga.search.data.es.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Instruction {
    String paymentTxnOrderId;
    String txnRefId;
    Date date;
    PaymentStatusEnum status;
}
