package com.tga.product.service.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tga.product.event.ProductAddedEvent;
import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.events.pubsub.ProductEventPublisher;
import com.tga.product.service.proxy.controller.InventoryServiceProxy;
import com.tga.product.service.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
//	private Logger logger =  LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private InventoryServiceProxy inventoryServiceProxy;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductEventPublisher productEventPublisher;
	@Value("${kafka.consumer.product.topic}")
    private String topic;
	
	@GetMapping("/products")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProducts() {
		log.info("Product service : getProducts");
		return productService.getProducts();
	}
	
	@GetMapping("/products/name")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProducts(String name) {
		log.info("Product service : getProducts with name : " + name);
		return productService.getProductsRegex(name);
	}
	
	@GetMapping("/products/list")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProducts(@RequestParam List<String> idList) {
		log.info("Product service : getProducts with idList : " + idList);
		return productService.getProductsList(idList);
	}
	
	@GetMapping("/inventory")
	@ResponseStatus(HttpStatus.OK)
	public Integer getInventory() {
		log.info("Product service : getInventory");
		return inventoryServiceProxy.getInventory();
	}
	
	@GetMapping("/product/name")
	@ResponseStatus(HttpStatus.OK)
	public Integer getProductDetail(@RequestParam String proStrin) {
		log.info("Product service : getInventory");
		return inventoryServiceProxy.getInventory();
	}
	
	@PostMapping("product")
	@ResponseStatus(HttpStatus.OK)
	public String addProduct(@RequestBody ProductDTO productDTO) {
		String productId = productService.save(productDTO);
		return productId;
	}
	//https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/
	@PostMapping("/publish")
    @ResponseStatus(HttpStatus.OK)
    public void publish(@RequestBody ProductAddedEvent productAddedEvent) {
        log.info("Received product added event: {}", productEventPublisher);
        productEventPublisher.publishToTopic(productAddedEvent,topic);
    }

	@PostMapping("loadData")
	public List<String> excelReader(@RequestParam String filePath) {
		log.info("Loading data into database");
		List<String> productIds = new LinkedList<>();
		try {
			FileInputStream file = new FileInputStream(
					new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);


			for(int i=1; i<sheet.getPhysicalNumberOfRows();i++) {
				XSSFRow row = sheet.getRow(i);
				ProductDTO productDTO = new ProductDTO(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),new ArrayList<>(Arrays.asList(row.getCell(3).toString().split(","))));
				productIds.add(productService.save(productDTO));
			}

			log.info("Loading data into database is successful");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productIds;
	}

}
