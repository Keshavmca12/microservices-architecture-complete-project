package com.tga.product.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tga.product.service.dto.SellerDTO;
import com.tga.product.service.service.SellerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/productSkus")
	@ResponseStatus(HttpStatus.OK)
	public List<SellerDTO> getProducts() {
		log.info("Seller service : getSellers");
		return sellerService.getSellers();
	}
	
	@PostMapping("productSku")
	@ResponseStatus(HttpStatus.OK)
	public String addProductSku(@RequestBody SellerDTO sellerDTO) {
		String sellerId = sellerService.save(sellerDTO);
		return sellerId;
	}
}
