package com.tga.search.handler;


import com.tga.eventsource.handler.EventListener;
import com.tga.product.event.ProductAddedEvent;
import com.tga.product.event.ProductModelAddedEvent;
import com.tga.product.event.ProductPriceUpdated;
import com.tga.product.event.ProductPublishedEvent;
import com.tga.search.data.es.product.ESCategory;
import com.tga.search.data.es.product.ESProduct;
import com.tga.search.data.es.repository.CategoryRepository;
import com.tga.search.data.es.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class ProductEventListener implements EventListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     *
     * @param event
     */
    public void handle(ProductAddedEvent event) {
        log.info("ProductEventListener: ProductAddedEvent");
        updateProduct(event.getProduct());
    }

    /**
     *
     * @param event
     */
    public void handle(ProductModelAddedEvent event) {
        log.info("ProductEventListener: ProductModelAddedEvent");
        updateProduct(event.getProduct());
    }

    /**
     *
     * @param event
     */
    public void handle(ProductPriceUpdated event) {
        log.info("ProductEventListener: ProductPriceUpdated");
        updateProduct(event.getProduct());
    }

    /**
     *
     * @param event
     */
    public void handle(ProductPublishedEvent event) {
      log.info("ProductEventListener: ProductPublishedEvent");
       updateProduct(event.getProduct());
    }

    /**
     *
     * @param product
     */
    private void updateProduct( com.tga.product.model.Product product){
        ESProduct esProduct = null;
        Optional<ESProduct> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()){
            esProduct = productOptional.get();
        }else{
            esProduct = new ESProduct();
        }
        esProduct.setId(product.getId());
        esProduct.setName(product.getName());
        esProduct.setModels(product.getModels());
        esProduct.setPublished(product.isPublished());

        Optional<ESCategory> categoryOptional = categoryRepository.findById(product.getCategoryId());
        if (categoryOptional.isPresent()){
            esProduct.setCategoryId(product.getCategoryId());
            esProduct.setCategoryName(categoryOptional.get().getName());
        }
        productRepository.save(esProduct);
    }
}
