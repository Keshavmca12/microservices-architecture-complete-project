package com.tga.product.service.controller;

import java.util.List;

import com.tga.product.service.events.pubsub.ProductEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.dto.ProductSkuDTO;
import com.tga.product.service.service.ProductSkuService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/productSku")
@Slf4j
public class ProductSkuController {

	@Autowired
	private ProductSkuService productSkuService;
	
	@GetMapping("/productSkus")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductSkuDTO> getProducts() {
		log.info("Product service : getProducts");
		return productSkuService.getProductSkus();
	}
	
	@PostMapping("productSku")
	@ResponseStatus(HttpStatus.OK)
	public String addProductSku(@RequestBody ProductSkuDTO productSkuDTO) {
		String productSkuId = productSkuService.save(productSkuDTO);
		return productSkuId;
	}

	@PostMapping("loadInventory")
	@ResponseStatus(HttpStatus.OK)
	public String loadInventory() {
		productSkuService.loadInventory();
		return "";
	}
}
