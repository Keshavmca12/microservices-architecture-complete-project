package com.tga.product.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.entity.Product;
import com.tga.product.service.repository.ProductRepository;
import com.tga.product.service.service.ProductService;
import com.tga.product.service.util.ModelMapperUtil;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String save(ProductDTO productDTO) {
		Product product = ModelMapperUtil.map(productDTO, Product.class);
		product = productRepository.insert(product);
		return product.getId();
	}

	@Override
	public List<ProductDTO> getProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = ModelMapperUtil.mapAll(products, ProductDTO.class);
		return productDTOs;
	}

	
	@Override
	public List<ProductDTO> getProductsRegex(String name) {
		List<Product> products = productRepository.findProductsByRegexpName( name );
		List<ProductDTO> productDTOs = ModelMapperUtil.mapAll(products, ProductDTO.class);
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductsList(List<String> idList) {
		List<Product> products = (List<Product>) productRepository.findAllById(idList);
		List<ProductDTO> productDTOs = ModelMapperUtil.mapAll(products, ProductDTO.class);
		return productDTOs;
	}


}
