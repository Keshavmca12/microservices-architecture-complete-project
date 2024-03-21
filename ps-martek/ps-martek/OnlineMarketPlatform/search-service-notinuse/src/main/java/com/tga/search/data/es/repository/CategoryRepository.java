package com.tga.search.data.es.repository;

import com.tga.search.data.es.product.ESCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<ESCategory, String> {

    Page<ESCategory> findByName(String name, Pageable pageable);

}