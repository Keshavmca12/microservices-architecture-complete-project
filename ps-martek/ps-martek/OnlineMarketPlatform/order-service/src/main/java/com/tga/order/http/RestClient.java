package com.tga.order.http;

import com.tga.customer.model.Customer;
import com.tga.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestClient {

    @Value("${search.service.base.url}")
    private String searchServiceBaseURL;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param customerId
     * @return
     */
    @Deprecated
    public Customer getCustomer(String customerId) {
        try {
            ResponseEntity<Customer> response = this.restTemplate.getForEntity(this.searchServiceBaseURL + "/customers/{id}", Customer.class, customerId);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Customer details: {}", response.getBody());
                return response.getBody();
            } else {
                log.error("Error while getting the customer with id {}", customerId);
                log.error("Error Status Code {}, with response body {} ", response.getStatusCode(), response.getBody());
                return null;
            }
        } catch (RestClientException rce) {
            log.error("Error while getting the customer with id {}, error message {}", customerId, rce.getMessage());
            return null;
        }
    }

    /**
     *
     * @param customerId
     * @param cartId
     * @return
     */

    @Deprecated
    public Order getOrderByCustomerIdAndCartId(String customerId, String cartId) {
        try {
            ResponseEntity<Order> response = this.restTemplate.getForEntity(this.searchServiceBaseURL + "/customers/{customerId}/carts/{cartId}/order",
                    Order.class, customerId, cartId);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Order details: {}", response.getBody());
                return response.getBody();
            } else {
                log.error("Error while getting the order  with cartId {}", cartId);
                log.error("Error Status Code {}, with response body {} ", response.getStatusCode(), response.getBody());
                return null;
            }
        } catch (RestClientException rce) {
            log.error("Error while getting the order  with cartId {}, error message {}", customerId, rce.getMessage());
            return null;
        }
    }
}
