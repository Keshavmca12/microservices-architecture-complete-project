package com.tga.product.service.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductImageDetailsDTO extends BaseDTO {

	
	private String imageName;

	private String imagePath;


}
