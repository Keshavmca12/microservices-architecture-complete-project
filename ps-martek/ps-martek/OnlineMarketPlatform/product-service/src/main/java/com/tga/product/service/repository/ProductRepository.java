package com.tga.product.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tga.product.service.entity.Product;

@Repository
public interface ProductRepository  extends MongoRepository<Product, String>{
	
	@Query("{ 'productName' : { $regex: ?0 } }")
	List<Product> findProductsByRegexpName(String regexp);

}
