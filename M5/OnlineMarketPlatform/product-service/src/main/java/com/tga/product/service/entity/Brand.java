package com.tga.product.service.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection  = "brand")
public class Brand extends BaseEntity{
	
	private String brandType;

	private String brandName;

	private String description;

}
