package com.tga.product.service.service;

import java.util.List;

import com.tga.product.service.dto.SellerDTO;

public interface SellerService {

	List<SellerDTO> getSellers();

	String save(SellerDTO sellerDTO);

}
