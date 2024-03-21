package com.tga.pos.customerservice.adapter.dto;

import lombok.*;
import lombok.extern.slf4j.*;

import javax.validation.constraints.*;

@Slf4j
@Getter
@ToString
@Data
public class CustomerRequest {

    private @NotNull String id;

    private String name ;

    private String email;

    private String phone;

    // update address one by one
    AddressRequest address;
    CardDetailsRequest cardDetails;




}
