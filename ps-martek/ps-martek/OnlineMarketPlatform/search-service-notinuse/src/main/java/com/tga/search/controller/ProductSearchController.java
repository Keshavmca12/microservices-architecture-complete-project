package com.tga.search.controller;

import com.tga.search.data.es.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ProductSearchController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String productId){
        log.info("REST API Controller - getProduct: {}", productId);
        return ResponseEntity.ok(productRepository.findById(productId));
    }
}
