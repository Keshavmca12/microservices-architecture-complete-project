package com.tga.pos.customerservice.adapter.service;

import com.tga.customer.event.*;
import com.tga.pos.customerservice.adapter.events.pubsub.*;
import com.tga.pos.customerservice.adapter.repository.*;
import com.tga.pos.customerservice.server.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tga.pos.customerservice.adapter.dto.*;
import com.tga.customer.model.*;
import com.tga.pos.customerservice.domainlayer.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {




    @Value("${kafka.consumer.customer-event-request-topic}")
    private String topic;


    private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddedPublisher customerAddedPublisher;


    @Override
    public Mono<Customer> findById(String id) {
        log.info("Find Customer By Id=" + id);

        if (id == null) {
            Mono<Customer> fallback = Mono.error(new InvalidInputException("Invalid Id: " + id));
            return fallback;
        }
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> save(CustomerRequest request) {

        if(null == request.getId() || null== request.getName() || null ==  request.getEmail()){

            Mono<Customer> fallback = Mono.error(new InvalidInputException("Invalid customer : " + request));
            return fallback;
        }

        log.debug("customer Request value :{},", request);
        System.out.println("customer request value "+ request);

        //return
        Customer newCustomer = new Customer();
        newCustomer.setId(request.getId());
        newCustomer.setName(request.getName());
        newCustomer.setEmail(request.getEmail());
        // Setting the address

        Address address = getAddress(request);
        CardDetails cardDetails= getCardDetails(request);
        newCustomer.setAddresses(new Address[]{address});
        newCustomer.setCardDetails(new CardDetails[]{cardDetails});


        CustomerAddedEvent customerAddedEvent = new CustomerAddedEvent();
        customerAddedEvent.setCustomer(newCustomer);
        log.info("Sending InventoryUpdateEventResponse to topic: {} and Payload: {}", topic, customerAddedEvent);

//        customerAddedPublisher.publishToTopic(customerAddedEvent,topic);


        return customerRepository.save(newCustomer);

    }

    private static Address getAddress(CustomerRequest request) {
        Address address = new Address();
        address.setCity(request.getAddress().getCity());
        address.setLine1(request.getAddress().getCity());
        address.setLine2(request.getAddress().getLine2());
        address.setPinCode(request.getAddress().getPinCode());
        address.setName(request.getAddress().getName());
        address.setBillingAddress(request.getAddress().isBillingAddress());
        return address;
    }


    private static CardDetails getCardDetails(CustomerRequest request) {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardName(request.getCardDetails().getCardName());
        cardDetails.setCardNo(request.getCardDetails().getCardNo());

        return cardDetails;
    }

    @Override
    public Mono<Customer> update(CustomerRequest request) {
        return null;
    }

    @Override
    public Mono<String> updateAddress(AddressRequest request) {

        if(null == request.getId() || null== request.getLine1() || null ==  request.getPinCode()){

            //Mono<Customer> fallback = Mono.error(new InvalidInputException("Invalid address : " + request));
            Mono<String> fallback = Mono.error(new InvalidInputException("Invalid address : " + request));
            return fallback;
        }

/*
        Mono<Customer> exisCustomer = customerRepository.findById(request.getId());
        log.debug(" existing customer : {}", exisCustomer);
*/

        return customerRepository.findById(request.getId()).flatMap(customer -> {

            // putting from AddressRequest To Address

            Address newAddress = new Address();
            extracted(request, newAddress);

            log.debug(" extracted from request new Address  {}", newAddress);
            Address[] existingAdd = customer.getAddresses();
           // increasing the size of new Address by 1
           Address[] newAddedAddress = new Address[existingAdd.length+1];
            newAddedAddress = Arrays.copyOf(existingAdd,existingAdd.length);
           newAddedAddress[newAddedAddress.length-1] = newAddress;

            log.debug(" array sizes before {} : and after  {}", existingAdd.length,newAddedAddress.length);
           customer.setId(request.getId());
            customer.setEmail(customer.getEmail());
            customer.setName(customer.getName());
           customer.setCardDetails(customer.getCardDetails());
           // adding the new address
           customer.setAddresses(newAddedAddress);
           // send to kafka
            customerRepository.deleteById(request.getId());
            return customerRepository.save(customer).map(s-> s.getId());
        }).switchIfEmpty(Mono.error(new DuplicateRecordException("Record Not Found=" + request.getId())));




    }

    private static void extracted(AddressRequest request, Address newAddress) {
        newAddress.setCity(request.getCity());
        newAddress.setLine1(request.getCity());
        newAddress.setLine2(request.getLine2());
        newAddress.setPinCode(request.getPinCode());
        newAddress.setName(request.getName());
        newAddress.setBillingAddress(request.isBillingAddress());
    }

    @Override
    public Mono<Customer> updateCardDetailsRequest(CustomerRequest request) {
        return null;
    }


    public Flux<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Address> findDeliveryAddressId(String id) {
        log.debug(" finding address for the customer id  : {}", id);
        Mono<Customer> customerMono =  customerRepository.findById(id);
        return customerMono.map( s -> s.getAddresses()[0]);
    }

    @Override
    public Mono<CardDetails> findPaymentInfoById(String id) {
        log.debug(" finding address for the customer id  : {}", id);
        Mono<Customer> customerMono =  customerRepository.findById(id);
        return customerMono.map( s -> s.getCardDetails()[0]);
    }
}
