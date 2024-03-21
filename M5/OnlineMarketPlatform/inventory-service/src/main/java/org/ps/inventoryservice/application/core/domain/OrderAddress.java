package org.ps.inventoryservice.application.core.domain;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class OrderAddress {
    private String id;
    private String fullAddress;
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
