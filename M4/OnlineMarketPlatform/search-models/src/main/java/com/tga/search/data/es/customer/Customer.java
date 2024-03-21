package com.tga.search.data.es.customer;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {

    private String id;
    private List<Address> addresses;
    private String contactDetails;
    private List<CardDetails> cardDetails;
    private String name;
    private String email;
    private Set<Order> orders;

}
