package com.tga.product.service.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BrandDTO extends BaseDTO{
	
	private String brandType;

	private String brandName;

	private String description;
	
	private List<String> productIds;

}
