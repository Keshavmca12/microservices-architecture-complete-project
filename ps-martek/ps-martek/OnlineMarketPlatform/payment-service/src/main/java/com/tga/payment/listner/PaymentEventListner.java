package com.tga.payment.listner;

import com.tga.order.event.OrderPlacedEvent;
import com.tga.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentEventListner {
    @Autowired
    private final PaymentService productService;



//    @KafkaListener(topics = "${kafka.payment.topic}", groupId = "test-consumer-group" )
//    public void consumeMessage(OrderPlacedEvent event) {
//        log.info("Got message <{}>",event);
//
//        Long savedId = productService.doPayment(event.getOrder());
//        log.info("Updated payment <{}>", savedId);
//
//    }




}
