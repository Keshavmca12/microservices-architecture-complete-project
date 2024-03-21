package com.tga.product.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SellerDTO extends BaseDTO {
	
	private String sellerName;

	private String partyId;

	private String description;

}
