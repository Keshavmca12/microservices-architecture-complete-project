package com.tga.search.controller;

import com.tga.search.data.es.customer.ESCustomer;
import com.tga.search.data.es.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class CustomerSearchController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String customerId){
        log.info("CustomerSearchController.getCustomer: {}", customerId);
        Optional<ESCustomer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            return ResponseEntity.ok(customerOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param customerId
     * @param orderId
     * @return
     */
    @GetMapping("/customers/{customerId}/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("customerId") String customerId, @PathVariable("id") String orderId){
        log.info("CustomerSearchController.getOrder: customerId: {}, orderId: {}", customerId, orderId);
        Optional<ESCustomer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent() && customerOptional.get().getOrders() != null){
            return ResponseEntity.ok(customerOptional.get().getOrders().stream().filter(o->orderId.equals(o.getId())).findFirst());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param customerId
     * @param cartId
     * @return
     */
    @GetMapping("/customers/{customerId}/carts/{cartId}/order")
    public ResponseEntity<?> getOrderByCartId(@PathVariable("customerId") String customerId, @PathVariable("cartId") String cartId){
        log.info("CustomerSearchController.getOrder: customerId: {}, cartId: {}", customerId, cartId);
        Optional<ESCustomer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent() && customerOptional.get().getOrders() != null){
            return ResponseEntity.ok(customerOptional.get().getOrders().stream().filter(o->cartId.equals(o.getCartId())).findFirst());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
