package com.tga.search;

import com.tga.search.handler.CartEventListener;
import com.tga.search.handler.CategoryEventListener;
import com.tga.search.handler.ProductEventListener;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Bean("Cart")
    public CartEventListener cartEventHandler(){
        return new CartEventListener();
    }

    @Bean("Product")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ProductEventListener productEventListener(){
        return new ProductEventListener();
    }

    @Bean("Category")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CategoryEventListener categoryEventListener(){
        return new CategoryEventListener();
    }
}
