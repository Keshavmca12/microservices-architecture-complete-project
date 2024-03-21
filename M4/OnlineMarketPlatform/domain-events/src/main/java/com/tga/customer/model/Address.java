package com.tga.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String pinCode;
    private String name;
    private boolean billingAddress;
}
