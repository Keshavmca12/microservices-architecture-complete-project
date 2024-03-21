package com.tga.order.repository;

import com.tga.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order, String>{


    @Query("{ 'orderId' : { $regex: ?0 } }")
    List<Order> findOrderByRegexpName(String regexp);


}
