package com.tga.product.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tga.product.service.entity.Seller;

@Repository
public interface SellerRepository  extends MongoRepository<Seller, String>{

}
