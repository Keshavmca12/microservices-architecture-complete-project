package com.tga.shipment.model;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Shipment {

    private String id;
    private String customerId;
    private String orderId;
    private String addressId;
    private ShipmentStatus status;

}
