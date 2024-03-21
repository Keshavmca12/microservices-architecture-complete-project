package com.tga.search.data.es.customer;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Shipment {

    private String id;
    private String addressId;

    @EqualsAndHashCode.Exclude
    private ShipmentStatus status;

}
