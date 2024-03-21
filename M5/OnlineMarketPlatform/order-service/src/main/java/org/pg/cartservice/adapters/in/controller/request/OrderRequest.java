package org.pg.cartservice.adapters.in.controller.request;

import lombok.*;
import org.pg.cartservice.application.core.domain.Item;
import org.pg.cartservice.application.core.domain.enums.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String id;
    private LocalDateTime orderDate;
    private List<Item> items;
    private PaymentDetails paymentDetails;
    private OrderStatusEnum orderStatus;
    private String userId;
    private OrderAddressRequest address;

    private OrderStatusEnum status;
}
