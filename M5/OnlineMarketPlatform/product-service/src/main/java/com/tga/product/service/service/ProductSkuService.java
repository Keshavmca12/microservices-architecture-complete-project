package com.tga.product.service.service;

import java.util.List;

import com.tga.product.service.dto.ProductSkuDTO;

public interface ProductSkuService {

	String save(ProductSkuDTO productSkuDTO);

	List<ProductSkuDTO> getProductSkus();

    void loadInventory();
}
