package com.tga.product.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tga.inventory.event.InventoryAddedEvent;
import com.tga.inventory.model.Inventory;
import com.tga.product.event.ProductAddedEvent;
import com.tga.product.service.entity.Product;
import com.tga.product.service.events.pubsub.InventoryEventPublisher;
import com.tga.product.service.events.pubsub.ProductEventPublisher;
import com.tga.product.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tga.product.service.dto.ProductSkuDTO;
import com.tga.product.service.entity.ProductSKU;
import com.tga.product.service.repository.ProductSkuRepository;
import com.tga.product.service.service.ProductSkuService;
import com.tga.product.service.util.ModelMapperUtil;

@Service
public class ProductSkuServiceImpl implements ProductSkuService {
	
	@Autowired
	private ProductSkuRepository productSkuRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InventoryEventPublisher inventoryEventPublisher;

	@Value("${kafka.consumer.inventory-added-request-topic}")
	private String topic;

	@Override
	public String save(ProductSkuDTO productSkuDTO) {
		ProductSKU productSKU = ModelMapperUtil.map(productSkuDTO, ProductSKU.class);
		productSKU = productSkuRepository.insert(productSKU);
		return productSKU.getId();
	}

	@Override
	public List<ProductSkuDTO> getProductSkus() {
		List<ProductSKU> productSKUs = productSkuRepository.findAll();
		List<ProductSkuDTO> productSkuDTOs = ModelMapperUtil.mapAll(productSKUs, ProductSkuDTO.class);
		return productSkuDTOs;
	}

	@Override
	public void loadInventory() {
		InventoryAddedEvent inventoryAddedEvent = new InventoryAddedEvent();
		List<Inventory> inventoryList = new ArrayList<>();
		List<ProductSKU> productSKUs = productSkuRepository.findAll();
		productSKUs.stream().forEach( sku -> {
			Inventory inventory = createInventoryEvent(sku);
			inventoryList.add(inventory);
		});
		inventoryAddedEvent.setInventoryList(inventoryList);
		inventoryEventPublisher.publishInventory(inventoryAddedEvent,topic);
	}

	private Inventory createInventoryEvent(ProductSKU productSku) {
		Inventory inventory = new Inventory();
		Product product = getProduct(productSku);
		inventory.setInventoryName(productSku.getName());
		inventory.setSkuId(productSku.getId());
		inventory.setProductId(product.getId());
		inventory.setQuantity(30);
		inventory.setPriceUnit(200d);
		inventory.setSupplierId(product.getSellerIds().get(0));
		return  inventory;
	}

	private Product getProduct(ProductSKU productSku){
		Optional<Product> product = productRepository.findById(productSku.getProductId());
		return product.get();
	}


}
