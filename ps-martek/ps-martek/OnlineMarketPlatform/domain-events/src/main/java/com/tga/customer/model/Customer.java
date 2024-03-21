package com.tga.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Slf4j
@Getter
@ToString
@Data

@Document("customers")
public class Customer {

    @Field(name = "_id")
    @Indexed
    private String id;
    private String name ;
    private String email;

    @Indexed
    private String mobileNumber;

    private Address[] addresses;
    private String contactDetails;
    private CardDetails[] cardDetails;
}

