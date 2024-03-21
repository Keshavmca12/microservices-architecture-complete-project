package com.tga.product.event;

import com.tga.product.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CategoryAddedEvent implements ProductEvent{

    private Category category;

}
