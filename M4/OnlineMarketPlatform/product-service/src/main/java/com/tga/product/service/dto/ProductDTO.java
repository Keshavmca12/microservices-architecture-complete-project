package com.tga.product.service.dto;

import java.util.List;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO extends BaseDTO {

	
	private String productName;

	private String description;

	private String categoryId;
	
	private List<String> sellerIds;

}
