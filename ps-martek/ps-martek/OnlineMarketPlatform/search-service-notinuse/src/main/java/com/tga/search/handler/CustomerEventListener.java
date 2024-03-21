package com.tga.search.handler;

import com.tga.customer.event.AddressAddedEvent;
import com.tga.customer.event.CardDetailsAddedEvent;
import com.tga.customer.event.CustomerAddedEvent;
import com.tga.eventsource.handler.EventListener;
import com.tga.search.data.es.customer.Address;
import com.tga.search.data.es.customer.CardDetails;
import com.tga.search.data.es.customer.ESCustomer;
import com.tga.search.data.es.repository.CustomerRepository;
import com.tga.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component("Customer")
public class CustomerEventListener implements EventListener {

    @Autowired
    private CustomerRepository customerRepository;
    /**
     *
     * @param event
     */
    public void handle(CustomerAddedEvent event) {
        log.info("CustomerEventListener: CustomerAddedEvent");
        createCustomer(event.getCustomer());
    }

    /**
     *
     * @param event
     */
    public void handle(CardDetailsAddedEvent event) {
        log.info("CustomerEventListener: CardDetailsAddedEvent");
        createCustomer(event.getCustomer());
    }

    /**
     *
     * @param event
     */
    public void handle(AddressAddedEvent event) {
        log.info("CustomerEventListener: AddressAddedEvent");
        createCustomer(event.getCustomer());
    }

    private void createCustomer(Customer customer) {
        Optional<ESCustomer> esCustomerOpt = customerRepository.findById(customer.getId());
        ESCustomer esCustomer = null;
        if (esCustomerOpt.isPresent()) {
            esCustomer = esCustomerOpt.get();
        } else {
            esCustomer = new ESCustomer();
        }
        esCustomer.setId(customer.getId());
        if (customer.getAddresses() != null){
            esCustomer.setAddresses(Stream.of(customer.getAddresses())
                    .map(address ->
                            new Address(address.getLine1(), address.getLine2(), address.getCity(), address.getPinCode(), address.getName(), address.isBillingAddress()))
                    .collect(Collectors.toList()));
        }

        esCustomer.setContactDetails(customer.getContactDetails());
        if(customer.getCardDetails() != null) {
            esCustomer.setCardDetails(
                    Stream.of(customer.getCardDetails())
                            .map(cardDetails -> new CardDetails(cardDetails.getCardNo(), cardDetails.getCardName()))
                            .collect(Collectors.toList()));
        }

        esCustomer.setName(customer.getName());
        esCustomer.setEmail(customer.getEmail());
        customerRepository.save(esCustomer);
    }

}
