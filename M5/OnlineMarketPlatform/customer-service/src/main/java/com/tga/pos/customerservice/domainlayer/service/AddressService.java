package com.tga.pos.customerservice.domainlayer.service;

import com.tga.pos.customerservice.adapter.dto.*;
import com.tga.customer.model.*;
import reactor.core.publisher.*;

public interface AddressService {

    public Mono<Customer> findById(Long id);
    public Mono<Customer> save(CustomerRequest request);

    public Mono<Customer> update(CustomerRequest request);

    public Mono<Customer> updateAddress(CustomerRequest request);

    public Mono<Customer> updateCardDetailsRequest(CustomerRequest request);

    public Flux<Customer> getAll() ;

}
