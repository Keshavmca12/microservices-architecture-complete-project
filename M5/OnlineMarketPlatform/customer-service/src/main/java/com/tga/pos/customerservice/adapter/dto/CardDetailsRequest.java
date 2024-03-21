package com.tga.pos.customerservice.adapter.dto;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsRequest {
    private Long id;
    private String cardNo;
    private String CardName;
}

