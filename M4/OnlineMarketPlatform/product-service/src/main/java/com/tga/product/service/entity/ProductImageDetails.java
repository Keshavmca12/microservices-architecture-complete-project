package com.tga.product.service.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductImageDetails extends BaseEntity {

	
	private String imageName;

	private String imagePath;


}
