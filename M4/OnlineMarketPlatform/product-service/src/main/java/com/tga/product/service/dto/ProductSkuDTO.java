package com.tga.product.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSkuDTO extends BaseDTO {

	
	private String name;

	private String type;
	
	private String productId;
	
}
