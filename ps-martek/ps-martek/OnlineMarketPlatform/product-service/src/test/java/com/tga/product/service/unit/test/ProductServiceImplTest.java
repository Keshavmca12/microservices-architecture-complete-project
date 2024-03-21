package com.tga.product.service.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.entity.Product;
import com.tga.product.service.repository.ProductRepository;
import com.tga.product.service.service.impl.ProductServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = ProductUnitTestConfig.class)
public class ProductServiceImplTest {
	
	
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    
    @Test
    public void testSave() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Test Product");
        productDTO.setDescription("Test product");
        Product product = new Product();
        product.setProductName("Test Product11");
        productDTO.setDescription("Test product11");
        product.setId("123");
        when(productRepository.insert(Mockito.any(Product.class))).thenReturn(product);
        String productId = productService.save(productDTO);
        Mockito.verify(productRepository, Mockito.times(1)).insert(Mockito.any(Product.class));
        assertEquals("123", productId);
    }
    
    @Test
    public void testGetProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setDescription("10.0");
        product1.setId("1");
        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setDescription("20.0");
        product2.setId("2");
        products.add(product1);
        products.add(product2);
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDTO> productDTOs = productService.getProducts();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
        assertEquals(2, productDTOs.size());
        assertEquals("Test Product 1", productDTOs.get(0).getProductName());
        assertEquals("10.0", productDTOs.get(0).getDescription());
        assertEquals("Test Product 2", productDTOs.get(1).getProductName());
        assertEquals("20.0", productDTOs.get(1).getDescription());
    }
    
    @Test
    public void testGetProductsRegex() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setDescription("10.0");
        product1.setId("1");
        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setDescription("20.0");
        product2.setId("2");
        products.add(product1);
        products.add(product2);
        when(productRepository.findProductsByRegexpName(Mockito.anyString())).thenReturn(products);
        List<ProductDTO> productDTOs = productService.getProductsRegex("Test");
        Mockito.verify(productRepository, Mockito.times(1)).findProductsByRegexpName(Mockito.anyString());
        assertEquals(2, productDTOs.size());
        assertEquals("Test Product 1", productDTOs.get(0).getProductName());
        assertEquals(Double.valueOf(10.0), productDTOs.get(0).getDescription());
        assertEquals("Test Product 2", productDTOs.get(1).getProductName());
        assertEquals(Double.valueOf(20.0), productDTOs.get(1).getDescription());
    }
    
    @Test
    public void testGetProductsList() {
        List<String> idList = new ArrayList<>();
        idList.add("1");
        idList.add("2");
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setDescription("10.0");
        product1.setId("1");
        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setDescription("20.0");
        product2.setId("2");
        products.add(product1);
        products.add(product2);
        when(productRepository.findAllById(idList)).thenReturn(products);
        List<ProductDTO> productDTOs = productService.getProductsList(idList);
        Mockito.verify(productRepository, Mockito.times(1)).findAllById(idList);

        assertEquals(2, productDTOs.size());
        assertEquals("Test Product 1", productDTOs.get(0).getProductName());
        assertEquals("10.0", productDTOs.get(0).getDescription());
        assertEquals("Test Product 2", productDTOs.get(1).getProductName());
        assertEquals("20.0", productDTOs.get(1).getDescription());
    }
}