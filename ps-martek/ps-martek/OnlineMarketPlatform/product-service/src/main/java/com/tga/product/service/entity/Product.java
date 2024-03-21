package com.tga.product.service.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection  = "product")
public class Product extends BaseEntity {

	
	private String productName;

	private String description;

	private String categoryId;
	
	private List<String> sellerIds;

}
