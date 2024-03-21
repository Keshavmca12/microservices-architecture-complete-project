package com.tga.search.handler;

import com.tga.eventsource.handler.EventListener;
import com.tga.search.data.es.customer.ESCustomer;
import com.tga.search.data.es.customer.Order;
import com.tga.search.data.es.customer.ShipmentStatus;
import com.tga.search.data.es.repository.CustomerRepository;
import com.tga.shipment.event.*;
import com.tga.shipment.model.Shipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Component("Shipment")
@Slf4j
public class OrderShipmentEventListener implements EventListener {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param event
     */
    public void handle(ShipmentCreatedEvent event) {
        log.info("OrderShipmentEventListener: ShipmentCreatedEvent");
        updateCustomerOrderShipment(event.getShipment());
    }

    /**
     *
     * @param event
     */
    public void handle(ShipmentOutForDeliverEvent event) {
        log.info("OrderShipmentEventListener: ShipmentOutForDeliverEvent");
        updateCustomerOrderShipment(event.getShipment());
    }

    /**
     *
     * @param event
     */
    public void handle(ShipmentDeliveredEvent event) {
        log.info("OrderShipmentEventListener: ShipmentDeliveredEvent");
        updateCustomerOrderShipment(event.getShipment());
    }

    /**
     *
     * @param event
     */
    public void handle(ShipmentReturnedEvent event) {
        log.info("OrderShipmentEventListener: ShipmentReturnedEvent");
        updateCustomerOrderShipment(event.getShipment());
    }

    /**
     *
     * @param event
     */
    public void handle(ShipmentCancelledEvent event) {
        log.info("OrderShipmentEventListener: ShipmentCancelledEvent");
        updateCustomerOrderShipment(event.getShipment());
    }

    /**
     *
     * @param shipment
     */
    private void updateCustomerOrderShipment(Shipment shipment) {
        Optional<ESCustomer> esCustomerOpt = customerRepository.findById(shipment.getCustomerId());
        ESCustomer esCustomer = null;
        if(esCustomerOpt.isPresent()){
            esCustomer = esCustomerOpt.get();
        }else{
            esCustomer = new ESCustomer();
        }
        esCustomer.setId(shipment.getCustomerId());

        Order esOrder = new Order();
        if(esCustomer.getOrders() == null){
            esCustomer.setOrders(new HashSet<>());
        }
        for(Order o: esCustomer.getOrders()){
            if(o.getId().equals(shipment.getOrderId())){
                esOrder = o;
                break;
            }
        }
        esCustomer.getOrders().add(getOrder(esOrder, shipment));
        customerRepository.save(esCustomer);
    }

    /**
     *
     * @param esOrder
     * @param shipment
     * @return
     */
    private Order getOrder(Order esOrder, Shipment shipment) {
        com.tga.search.data.es.customer.Shipment esShipment = new com.tga.search.data.es.customer.Shipment();
        esShipment.setId(shipment.getId());
        esShipment.setAddressId(shipment.getAddressId());
        esShipment.setStatus(ShipmentStatus.valueOf(shipment.getStatus().name()));
        esOrder.setShipmentDetail(esShipment);
        return esOrder;
    }
}
