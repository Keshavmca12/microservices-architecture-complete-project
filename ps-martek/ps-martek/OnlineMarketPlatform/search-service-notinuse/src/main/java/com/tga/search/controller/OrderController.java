package com.tga.search.controller;

import com.tga.search.data.es.customer.ESCustomer;
import com.tga.search.data.es.order.ESOrder;
import com.tga.search.data.es.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @param customerId
     * @param cartId
     * @return
     */
    @PostMapping("/customers/orders")
    public ResponseEntity<?> createorderByOrderId(ESOrder esOrder) {
        log.info("OrderCreateController.getOrder: customerId: {}", esOrder.getCustomerId());
        Optional<ESCustomer> customerOptional = customerRepository.findById(esOrder.getCustomerId());
        if (customerOptional.isPresent() && customerOptional.get().getOrders() != null) {
            return ResponseEntity.ok(customerOptional.get().getOrders().stream().filter(o -> o.getCartId().equals(o.getCartId())).findFirst());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
