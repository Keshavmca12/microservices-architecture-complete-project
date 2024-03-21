package com.tga.search.data.es.order;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "order")
@Data
public class ESOrder {

    private String customerId;
    private List<String> orderId;
}
