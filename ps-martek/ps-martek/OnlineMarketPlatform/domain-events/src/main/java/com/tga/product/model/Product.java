package com.tga.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product  {

    private String id;
    private String name;
    private String categoryId;
    private List<Model> models;
    private boolean isPublished;

}
