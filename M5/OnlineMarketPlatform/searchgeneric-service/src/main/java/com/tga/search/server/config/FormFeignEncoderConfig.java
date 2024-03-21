package com.tga.search.server.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import feign.form.spring.SpringFormEncoder;

@Configuration(proxyBeanMethods = false)
public class FormFeignEncoderConfig {
    @Value("${auth.server.username}")
    private String username;
    @Bean
    public Encoder formencoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, "turbopassword");
    }

}
