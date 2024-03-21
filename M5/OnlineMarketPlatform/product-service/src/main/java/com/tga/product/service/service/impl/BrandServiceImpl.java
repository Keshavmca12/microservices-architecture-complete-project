package com.tga.product.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tga.product.service.dto.BrandDTO;
import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.dto.SellerDTO;
import com.tga.product.service.entity.Brand;
import com.tga.product.service.entity.Product;
import com.tga.product.service.entity.Seller;
import com.tga.product.service.repository.BrandRepository;
import com.tga.product.service.repository.ProductRepository;
import com.tga.product.service.repository.SellerRepository;
import com.tga.product.service.service.BrandService;
import com.tga.product.service.service.ProductService;
import com.tga.product.service.service.SellerService;
import com.tga.product.service.util.ModelMapperUtil;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	

	@Override
	public List<BrandDTO> getBrands() {
		List<Brand> brands = brandRepository.findAll();
		List<BrandDTO> brandDTOs = ModelMapperUtil.mapAll(brands, BrandDTO.class);
		return brandDTOs;
		
	}

	@Override
	public String save(BrandDTO brandDTO) {
		Brand brand = ModelMapperUtil.map(brandDTO, Brand.class);
		brand = brandRepository.insert(brand);
		return brand.getId();
	}


}
