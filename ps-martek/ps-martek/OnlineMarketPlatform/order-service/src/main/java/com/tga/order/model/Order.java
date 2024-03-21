package com.tga.order.model;


import com.tga.eventsource.command.Aggregate;
import com.tga.eventsource.event.Event;
import com.tga.eventsource.event.NoActionEvent;
import com.tga.eventsource.util.EventUtil;
import com.tga.order.command.CreateOrderCommand;
import com.tga.order.event.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Getter
@ToString
@Setter
@Document(collection  = "order")
public class Order extends Aggregate {

    private String id;
    private String customerId;
    //private String cartId;
    private List<Item> items;
    private OrderStatusEnum status; //ORDER_CREATED, ORDER_PAYMENT_SUCCESS, ORDER_PAYMENT_FAILED, ORDER_PLACED, ORDER_CANCELLED, ORDER_DELIVERED
    private Double totalPrice;
    private String addressId;
    private String comments;

    @Override
    public void setId(String id) {
        this.id = id;
    }



    /**
     *
     * @param cmd
     * @return
     */
    public List<Event> process(CreateOrderCommand cmd) {
        log.info("CreateOrderCommand Command: order details: "+ cmd.toString());
        //validate here before raising the command event
        //convert command to event
        if(cmd.getId() == null){
            cmd.setId(UUID.randomUUID().toString());
        }
        setId(cmd.getId());
        return EventUtil.events(cmd);
    }

    /**
     *
     * @param cmdEvent
     */
    public List<Event> apply(CreateOrderCommand cmdEvent){
        log.info("CreateOrderCommand Event: order details: "+ cmdEvent.toString());
       this.items = cmdEvent.getItems();
       this.status = OrderStatusEnum.ORDER_CREATED;
       this.addressId = cmdEvent.getAddressId();
       this.customerId = cmdEvent.getCustomerId();
       //this.cartId = cmdEvent.getCartId();
       this.totalPrice = cmdEvent.getItems().stream().collect(Collectors.summingDouble(value -> value.getUnit()* value.getPrice()));
       return EventUtil.events( new OrderCreatedEvent(this));
    }

    public List<Event> apply(OrderCreatedEvent event){
        log.info("OrderCreatedEvent: order details: "+ event.getOrder());
        return EventUtil.events(new NoActionEvent(this));
    }


    /**
     *
     * @param event
     */
    public List<Event> apply(OrderPlacedEvent event){
        log.info("OrderPlaced: "+ event.getOrder());
        this.status = OrderStatusEnum.ORDER_PLACED;
        return EventUtil.events(new OrderPlacedEvent(this));
    }

}
