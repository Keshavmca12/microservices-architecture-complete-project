package com.tga.pos.customerservice.domainlayer.service;

import com.tga.pos.customerservice.adapter.dto.*;
import com.tga.customer.model.*;
import reactor.core.publisher.*;

public interface CustomerService {

    public Mono<Customer> findById(String id);
    public Mono<Customer> save(CustomerRequest request);

    public Mono<Customer> update(CustomerRequest request);

    public Mono<String> updateAddress(AddressRequest request);

    public Mono<Customer> updateCardDetailsRequest(CustomerRequest request);

    public Flux<Customer> getAll() ;

    public Mono<Address> findDeliveryAddressId(String id);

    public Mono<CardDetails> findPaymentInfoById(String id);

}
