package com.tga.product.service.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "seller")
public class Seller extends BaseEntity {
	
	private String sellerName;

	private String partyId;

	private String description;

}
