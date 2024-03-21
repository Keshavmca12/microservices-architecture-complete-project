package com.tga.search.handler;

import com.tga.eventsource.handler.EventListener;
import com.tga.product.event.CategoryAddedEvent;
import com.tga.search.data.es.product.ESCategory;
import com.tga.search.data.es.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class CategoryEventListener implements EventListener {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     *
     * @param event
     */
    public void handle(CategoryAddedEvent event) {
        log.info("CategoryEventListener: CategoryAddedEvent");
        ESCategory category = null;
        Optional<ESCategory> categoryOptional = categoryRepository.findById(event.getCategory().getId());
        if (categoryOptional.isPresent()){
            category = categoryOptional.get();
        }else{
            category = new ESCategory();
        }
        category.setId(event.getCategory().getId());
        category.setName(event.getCategory().getName());
        categoryRepository.save(category);
    }

}
