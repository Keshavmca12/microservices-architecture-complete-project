package com.tga.search.data.es.customer;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Set;

@Document(indexName = "customer")
@Data
public class ESCustomer {

    @Id
    private String id;
    private List<Address> addresses;
    private String contactDetails;
    private List<CardDetails> cardDetails;
    private String name;
    private String email;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Order> orders;

}
