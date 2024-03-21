package com.tga.search.handler;


import com.tga.eventsource.handler.EventListener;
import com.tga.payment.event.PaymentCaptured;
import com.tga.payment.event.PaymentFailed;
import com.tga.payment.event.PaymentSuccess;
import com.tga.payment.event.PaymentTxnCreated;
import com.tga.payment.model.Payment;
import com.tga.search.data.es.customer.*;
import com.tga.search.data.es.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component("Payment")
public class OrderPaymentEventListener implements EventListener {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param event
     */
    public void handle(PaymentTxnCreated event) {
        log.info("OrderPaymentEventListener: PaymentTxnCreated");
        updateCustomerOrderPayment(event.getPayment());
    }

    /**
     *
     * @param event
     */
    public void handle(PaymentSuccess event) {
        log.info("OrderPaymentEventListener: PaymentSuccess");
        updateCustomerOrderPayment(event.getPayment());
    }

    /**
     *
     * @param event
     */
    public void handle(PaymentCaptured event) {
        log.info("OrderPaymentEventListener: PaymentCaptured");
        updateCustomerOrderPayment(event.getPayment());
    }

    /**
     *
     * @param event
     */
    public void handle(PaymentFailed event) {
        log.info("OrderPaymentEventListener: PaymentFailed");
        updateCustomerOrderPayment(event.getPayment());
    }

    private void updateCustomerOrderPayment(Payment payment) {
        Optional<ESCustomer> esCustomerOpt = customerRepository.findById(payment.getCustomerId());
        ESCustomer esCustomer = null;
        if(esCustomerOpt.isPresent()){
            esCustomer = esCustomerOpt.get();
        }else{
            esCustomer = new ESCustomer();
        }
        esCustomer.setId(payment.getCustomerId());

        Order esOrder = new Order();
        if(esCustomer.getOrders() == null){
            esCustomer.setOrders(new HashSet<>());
        }
        for(Order o: esCustomer.getOrders()){
            if(payment.getOrderId().equals(o.getId())){
                esOrder = o;
                break;
            }
        }
        esCustomer.getOrders().add(getOrder(esOrder, payment));
        customerRepository.save(esCustomer);
    }

    /**
     *
     * @param esOrder
     * @param payment
     * @return
     */
    private Order getOrder(Order esOrder, Payment payment) {
        com.tga.search.data.es.customer.Payment esPayment = new com.tga.search.data.es.customer.Payment();
        esPayment.setId(payment.getId());
        if(payment.getInstructions() != null) {
            esPayment.setInstructions(payment.getInstructions().stream().map(i -> new Instruction(i.getPaymentTxnOrderId(), i.getTxnRefId(), i.getDate(), PaymentStatusEnum.valueOf(i.getStatus().name()))).collect(Collectors.toList()));
        }
        esPayment.setStatus(PaymentTxnStatus.valueOf(payment.getStatus().name()));
        esPayment.setTotalAmount(payment.getTotalAmount());
        esOrder.setPaymentDetail(esPayment);
        return esOrder;
    }


}
