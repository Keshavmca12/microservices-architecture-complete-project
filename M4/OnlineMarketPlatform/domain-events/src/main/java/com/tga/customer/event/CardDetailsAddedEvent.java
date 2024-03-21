package com.tga.customer.event;

import com.tga.customer.model.Customer;
import com.tga.eventsource.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CardDetailsAddedEvent implements Event {
    private Customer customer;
}
