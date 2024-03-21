package com.tga.search.adapters.client;

import com.tga.search.adapters.dto.ProductDTO;
import com.tga.search.server.config.FormFeignEncoderConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="productClient",url="${product.service.url}", path ="/product",
        configuration = FormFeignEncoderConfig.class)
public interface ProductClient {
    @RequestMapping(method = RequestMethod.GET, value="/products/name")
    List<ProductDTO> getProductByName(@RequestParam("name") String name);
}
