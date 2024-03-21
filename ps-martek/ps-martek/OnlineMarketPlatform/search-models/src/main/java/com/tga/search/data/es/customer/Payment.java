package com.tga.search.data.es.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Payment {
    private String id;

    @EqualsAndHashCode.Exclude
    private double totalAmount;

    @EqualsAndHashCode.Exclude
    private PaymentTxnStatus status;

    @EqualsAndHashCode.Exclude
    private List<Instruction> instructions;
}
