package com.tga.search.data.es.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private boolean isPublished;
    private String categoryId;
    private String categoryName;
    private List<Model> models;

}
