package com.tga.product.service.controller;

import java.util.List;

import com.tga.product.service.event.ProductEventConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tga.product.service.dto.CategoryDTO;
import com.tga.product.service.exception.ProductServiceException;
import com.tga.product.service.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(ProductEventConstants.ADD_CATEGORY)
	@ResponseStatus(HttpStatus.OK)
	public String addCategory(@RequestBody CategoryDTO category) {
		String categoryId = categoryService.save(category);
		return categoryId;
	}

	@GetMapping(ProductEventConstants.GET_CATEGORY)
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryDTO> getCategories() {
		return categoryService.getCategory();
	}
	
	@PutMapping(ProductEventConstants.UPDATE_CATEGORY)
	@ResponseStatus(HttpStatus.OK)
	public String updateCategory(@RequestBody CategoryDTO category) {
		String categoryId = null;
		try {
			categoryId = categoryService.update(category);
		} catch (ProductServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryId;
	}

}
