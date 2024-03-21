package com.tga.product.service.proxy.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inventory-service", url = "localhost:8080//inventory-service")
public interface InventoryServiceProxy {
	
	@GetMapping("/inventory")
	public Integer getInventory();

}
