package com.tga.search.handler;

import com.tga.cart.event.CartCheckedOutEvent;
import com.tga.cart.event.ItemAddedEvent;
import com.tga.cart.event.ItemRemovedEvent;
import com.tga.eventsource.handler.EventHandler;
import com.tga.eventsource.handler.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class CartEventListener implements EventListener {


    /**
     *
     * @param event
     */
    public void handle(ItemAddedEvent event) {
        log.info("CartEventHandler: ItemAddedEvent: No Action taken");
    }

    /**
     *
     * @param event
     */
    public void handle(ItemRemovedEvent event) {
        log.info("CartEventHandler: ItemRemovedEvent: No Action taken");
    }

    /**
     *
     * @param event
     */
    public void handle(CartCheckedOutEvent event) {
        log.info("CartEventHandler: CartCheckedOutEvent: No Action taken");
    }
}
