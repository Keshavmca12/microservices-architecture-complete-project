package com.tga.search.data.es.customer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address {

    private String line1;
    private String line2;
    private String city;
    private String pinCode;
    private String name;
    private boolean billingAddress;

}
