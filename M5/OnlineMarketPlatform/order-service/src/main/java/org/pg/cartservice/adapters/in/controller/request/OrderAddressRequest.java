package org.pg.cartservice.adapters.in.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderAddressRequest {
    private String id;
    private String fullAddress;
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
