package com.tga.search.data.es.repository;

import com.tga.search.data.es.product.ESProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ESProduct, String> {

    Page<ESProduct> findByModelsName(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"models.name\": \"?0\"}}]}}")
    Page<ESProduct> findByModelNameUsingCustomQuery(String name, Pageable pageable);

}
