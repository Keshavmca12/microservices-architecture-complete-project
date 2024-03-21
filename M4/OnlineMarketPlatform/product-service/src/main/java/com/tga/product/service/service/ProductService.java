package com.tga.product.service.service;

import java.util.List;

import com.tga.product.service.dto.ProductDTO;

public interface ProductService {

	String save(ProductDTO productDTO);

	List<ProductDTO> getProducts();

	List<ProductDTO> getProductsRegex(String name);

	List<ProductDTO> getProductsList(List<String> idList);

}
