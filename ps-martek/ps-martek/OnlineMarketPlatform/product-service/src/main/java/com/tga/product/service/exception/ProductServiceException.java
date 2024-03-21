package com.tga.product.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263577563129157997L;
	
	private String message;
	private String code;
	
	public ProductServiceException(String message, String code) {
		this.message = message;
		this.code = code;
	}
	
	public ProductServiceException(String message) {
		this.message = message;
	}
	
	

}
