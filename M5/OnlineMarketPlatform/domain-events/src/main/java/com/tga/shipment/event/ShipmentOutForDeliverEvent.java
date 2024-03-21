package com.tga.shipment.event;

import com.tga.shipment.model.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ShipmentOutForDeliverEvent implements ShipmentEvent {
    private Shipment shipment;
}