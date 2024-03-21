package com.tga.search.adapters.client;

import com.tga.search.adapters.dto.PublicKeyDTO;
import com.tga.search.server.config.FormFeignEncoderConfig;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="authClient",url="${auth.server.url}", path ="/oauth",
        configuration = FormFeignEncoderConfig.class)
public interface AuthDetailsClient {
    @RequestMapping(method = RequestMethod.GET, value="/token_key")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    PublicKeyDTO getPublicKey(@RequestParam Map<String, ?> formParams);
}
