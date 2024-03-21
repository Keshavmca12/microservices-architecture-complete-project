package com.tga.search.data.es.customer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CardDetails {
    private String cardNo;
    private String cardName;
}
