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

import com.tga.product.service.dto.BrandDTO;
import com.tga.product.service.dto.SellerDTO;
import com.tga.product.service.service.BrandService;
import com.tga.product.service.service.SellerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/seller")
@Slf4j
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@GetMapping("/brands")
	@ResponseStatus(HttpStatus.OK)
	public List<BrandDTO> getBrands() {
		log.info("Brand service : getBrands");
		return brandService.getBrands();
	}
	
	@PostMapping("brand")
	@ResponseStatus(HttpStatus.OK)
	public String addProductSku(@RequestBody BrandDTO brandDTO) {
		String brandId = brandService.save(brandDTO);
		return brandId;
	}
}
