package com.tga.search.data.es.product;

import com.tga.product.model.Category;
import com.tga.product.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "product")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ESProduct {

    @Id
    private String id;
    private String name;
    private boolean isPublished;
    private String categoryId;
    private String categoryName;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Model> models;

}
