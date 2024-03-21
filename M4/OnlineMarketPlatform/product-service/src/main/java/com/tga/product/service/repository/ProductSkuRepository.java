package com.tga.product.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tga.product.service.entity.ProductSKU;

@Repository
public interface ProductSkuRepository  extends MongoRepository<ProductSKU, String>{

}
