package com.tga.payment.service;

import com.tga.order.model.Order;
import com.tga.payment.dto.PaymentRequest;
import com.tga.payment.dto.PaymentResponse;

public interface PaymentService {
    Long doPayment(Order paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
