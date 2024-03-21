package com.tga.pos.customerservice.adapter.repository;

import com.tga.customer.model.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

	public Flux<Address> findAddressByname(String name);

	@Query("{ 'addresses': { 'line1': ?0}}")
	public Mono<Address> findAddressByLine1(String line);

	public Flux<CardDetails> findCardDetailsByname(String name);

	public Flux<CardDetails> findCardDetailsById(Long id);


}
