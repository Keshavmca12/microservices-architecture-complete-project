package com.tga.product.service.service;

import java.util.List;

import com.tga.product.service.dto.BrandDTO;
import com.tga.product.service.dto.SellerDTO;

public interface BrandService {

	List<BrandDTO> getBrands();

	String save(BrandDTO brandDTO);

}
