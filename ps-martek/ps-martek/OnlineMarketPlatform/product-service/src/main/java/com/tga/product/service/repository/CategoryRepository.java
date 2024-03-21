package com.tga.product.service.repository;


import  org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tga.product.service.entity.Category;

/**
 * TODO :  Integer vs string for mongo repository
 * @author keskumar2
 *
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer>  {

	
}
