package com.tga.payment.model;

import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.util.EventUtil;
import com.tga.payment.command.InvokePaymentCommand;
import com.tga.payment.event.PaymentCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Slf4j
@Getter
@ToString
@Setter
//@Document(collection  = "order")
@Component
public class Payment extends Aggregate {
    private long orderId;

    private String paymentMode;

    private String referenceNumber;

    private Instant paymentDate;

    private PaymentStatusEnum paymentStatus;

    private Double amount;
    private String id;
//    public List<Event> process(InvokePaymentCommand cmd) {
//        log.info("CreateOrderCommand Command: order details: "+ cmd.toString());
//        //validate here before raising the command event
//        //convert command to event
//        if(cmd.getId() == null){
//            cmd.setId(UUID.randomUUID().toString());
//        }
//        setId(cmd.getId());
//        return EventUtil.events(cmd);
//    }

    public List<Event> apply(InvokePaymentCommand cmdEvent){
        log.info("InvokePaymentCommand Event: order details: "+ cmdEvent.toString());
        this.paymentDate = cmdEvent.getPaymentDate();
        this.paymentStatus = PaymentStatusEnum.CAPTURED;
        this.amount = cmdEvent.getAmount();
        this.paymentMode = cmdEvent.getPaymentMode();
        this.referenceNumber = cmdEvent.getReferenceNumber();
        this.orderId = cmdEvent.getOrderId();
        return EventUtil.events( new PaymentCreatedEvent(this));
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }
}
