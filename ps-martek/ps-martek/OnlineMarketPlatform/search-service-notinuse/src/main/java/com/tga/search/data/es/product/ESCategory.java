package com.tga.search.data.es.product;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "category")
@Data
public class ESCategory {

    @Id
    private String id;
    private String name;

}
