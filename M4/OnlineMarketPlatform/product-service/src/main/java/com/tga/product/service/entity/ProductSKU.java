package com.tga.product.service.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection  = "productSku")
public class ProductSKU extends BaseEntity {

	
	private String name;

	private String type;
	
	private String productId;
	
}
