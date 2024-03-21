package com.tga.product.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.tga.product.service.dto.CategoryDTO;
import com.tga.product.service.entity.Category;
import com.tga.product.service.exception.ProductServiceException;
import com.tga.product.service.repository.CategoryRepository;
import com.tga.product.service.service.CategoryService;
import com.tga.product.service.util.ModelMapperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String save(CategoryDTO categoryDTO) {
		Category category = ModelMapperUtil.map(categoryDTO, Category.class);
		category = categoryRepository.insert(category);
		return category.getId();
	}

	@Override
	public List<CategoryDTO> getCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = ModelMapperUtil.mapAll(categories, CategoryDTO.class);
		return categoryDTOs;
	}

	/**
	 * Update not working have to see
	 */
	@Override
	public String update(CategoryDTO categoryDTO) throws ProductServiceException {
		log.info("Received category to update : {}", categoryDTO);

		if (categoryDTO.getId() == null) {
			throw new ProductServiceException("Category not found");
		}
		
		Category optional = mongoTemplate.findById(categoryDTO.getId(), Category.class);
		if (optional == null) {
			throw new ProductServiceException("Category not found");
		}

		Category categoryData = ModelMapperUtil.map(categoryDTO, Category.class);
	//	categoryRepository.save(categoryData);
		// mongoTemplate.up
		mongoTemplate.save(categoryData);
		return optional.getId();
		
		
	/*	Query query = new Query().addCriteria(Criteria.where("_id").is(categoryDTO.getId()));
        Update updateDefinition = new Update().set("cityName", city.getCityName());

        UpdateResult updateResult = mongoTemplate.save(query, updateDefinition, City.class);
        return updateResult.getUpsertedId().toString(); */
	}

}
