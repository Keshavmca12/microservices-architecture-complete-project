package com.tga.pos.customerservice.adapter.dto;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String id;
    private String line1;
    private String line2;
    private String city;
    private String pinCode;
    private String name;
    private boolean billingAddress;
}


