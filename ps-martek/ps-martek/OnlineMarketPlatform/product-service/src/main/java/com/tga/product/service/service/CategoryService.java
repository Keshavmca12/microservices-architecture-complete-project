package com.tga.product.service.service;

import java.util.List;

import com.tga.product.service.dto.CategoryDTO;
import com.tga.product.service.exception.ProductServiceException;

public interface CategoryService {

	String save(CategoryDTO categoryDTO);
	
	List<CategoryDTO> getCategory();

	String update(CategoryDTO categoryDTO) throws ProductServiceException;

}
