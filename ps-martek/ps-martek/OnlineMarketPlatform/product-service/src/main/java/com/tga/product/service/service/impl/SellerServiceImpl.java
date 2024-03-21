package com.tga.product.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.dto.SellerDTO;
import com.tga.product.service.entity.Product;
import com.tga.product.service.entity.Seller;
import com.tga.product.service.repository.ProductRepository;
import com.tga.product.service.repository.SellerRepository;
import com.tga.product.service.service.ProductService;
import com.tga.product.service.service.SellerService;
import com.tga.product.service.util.ModelMapperUtil;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<SellerDTO> getSellers() {
		List<Seller> sellers = sellerRepository.findAll();
		List<SellerDTO> sellerDTOs = ModelMapperUtil.mapAll(sellers, SellerDTO.class);
		return sellerDTOs;
	}

	@Override
	public String save(SellerDTO sellerDTO) {
		Seller seller = ModelMapperUtil.map(sellerDTO, Seller.class);
		seller = sellerRepository.insert(seller);
		return seller.getId();
	}


}
