package com.tga.search.data.es.repository;

import com.tga.search.data.es.customer.ESCustomer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<ESCustomer, String> {

}
