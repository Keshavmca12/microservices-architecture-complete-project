package com.tga.search.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

	
	private String productName;

	private String description;

	private String categoryId;
	
	private List<String> sellerIds;

}
