package com.tga.payment.service;

import com.tga.order.model.Order;
import com.tga.order.model.OrderStatusEnum;
import com.tga.payment.data.TransactionDetails;
import com.tga.payment.dto.PaymentMode;
import com.tga.payment.dto.PaymentResponse;
import com.tga.payment.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public Long doPayment(Order order) {
        log.info("Recording Payment Details: {}", order);

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentDate(Instant.now())
//                .paymentMode(paymentRequest.getPaymentMode())
                .paymentStatus(OrderStatusEnum.ORDER_CREATED.name())
                .orderId(Long.parseLong(order.getId()))
//                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(order.getTotalPrice())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());

        return Long.parseLong(transactionDetails.getId());
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);

        TransactionDetails transactionDetails
                = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(Long.parseLong(transactionDetails.getId()))
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        return paymentResponse;
    }
}
