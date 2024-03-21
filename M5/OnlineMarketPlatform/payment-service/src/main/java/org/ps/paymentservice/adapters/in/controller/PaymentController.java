package org.ps.paymentservice.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.ports.in.FindPaymentByOrderIdInputPort;
import org.ps.paymentservice.application.ports.in.OrderPaymentInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    FindPaymentByOrderIdInputPort findPaymentByOrderIdInputPort;

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable("orderId")
                                                       @Valid String orderId) {
        log.info("PaymentController - Get Payment for order id [{}]", orderId);
        return ResponseEntity.ok(findPaymentByOrderIdInputPort.execute(orderId));
    }
}
