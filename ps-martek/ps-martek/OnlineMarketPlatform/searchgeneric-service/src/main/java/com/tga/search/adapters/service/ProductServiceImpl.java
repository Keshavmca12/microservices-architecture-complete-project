/**
 * (C) Copyright 2021 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tga.search.adapters.service;

import com.tga.search.adapters.client.AuthDetailsClient;
import com.tga.search.adapters.client.ProductClient;
import com.tga.search.adapters.dto.ProductDTO;
import com.tga.search.adapters.dto.PublicKeyDTO;
import com.tga.search.domain.ports.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @version:
 * @date:
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    // Set Logger -> Lookup will automatically determine the class name.
    private static final Logger log = getLogger(lookup().lookupClass());
    @Autowired
    private AuthDetailsClient authDetailsClient;

    @Autowired
    private ProductClient productClient;
    /**
     * Get products by keyword search
     */
    @Override
    public List<ProductDTO> getProductsList(String containsQuery) {
        List<ProductDTO> list=productClient.getProductByName(containsQuery);
        return list;
    }

    /**
     * Search for the Product By the Product Names Like 'name'
     * @param _name
     * @return
     */
    @Override
    public List<PublicKeyDTO> getUserDetailsLogged(String containsQuery, String token) {
        Map<String, ?> formParams=new HashMap<>(){{
            put("token",token);
        }};
        PublicKeyDTO publicKeyDTO = authDetailsClient.getPublicKey(formParams);
        return Arrays.asList(publicKeyDTO);
    }
}
