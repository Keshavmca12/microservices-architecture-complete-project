package com.tga.product.service.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.entity.Product;
import com.tga.product.service.repository.ProductRepository;
import com.tga.product.service.util.ModelMapperUtil;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
classes = ProductIntegrationTestConfig.class)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProducts() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Test Product");
        productDTO.setDescription("10.0");

        productRepository.insert(ModelMapperUtil.map(productDTO, Product.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/product/products"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        List<ProductDTO> productDTOs = objectMapper.readValue(responseContent, new TypeReference<List<ProductDTO>>() {});

        assertEquals(1, productDTOs.size());
        assertEquals("Test Product", productDTOs.get(0).getProductName());
        assertEquals("10.0", productDTOs.get(0).getDescription());
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Test Product");
        productDTO.setDescription("10.0");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/product/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String productId = result.getResponse().getContentAsString();

        assertNotNull(productId);

        Product product = productRepository.findById(productId).orElse(null);

        assertNotNull(product);
        assertEquals("Test Product", product.getProductName());
        assertEquals("10.0", product.getDescription());
    }

  /*  @Test
    public void testLoadData() throws Exception {
        File file = new File("src/test/resources/testdata.xlsx");
        byte[] fileContent = Files.readAllBytes(file.toPath());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/product/loadData")
                        .file(new MockMultipartFile("file", "testdata.xlsx", "application/vnd.ms-excel", fileContent)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        List<String> productIds = objectMapper.readValue(responseContent, new TypeReference<List<String>>() {});

        assertEquals(3, productIds.size());

        Product product1 = productRepository.findById(productIds.get(0)).orElse(null);
        Product product2 = productRepository.findById(productIds.get(1)).orElse(null);
        Product product3 = productRepository.findById(productIds.get(2)).orElse(null);

        assertNotNull(product1);
        assertEquals("Product 1", product1.getProductName());
        assertEquals("10.0", product1.getDescription());
        assertEquals("Category 1", product1.getCategoryId());
     //   assertEquals(Arrays.asList("Feature 1", "Feature 2"), product1.getFeatures());

        assertNotNull(product2);
        assertEquals("Product 2", product2.getProductName());
        assertEquals("20.0", product2.getDescription());
        assertEquals("Category 1", product2.getCategoryId());
       // assertEquals(Arrays.asList("Feature 2", "Feature 3"), product2.getFeatures());

        assertNotNull(product3);
        assertEquals("Product 3", product3.getProductName());
        assertEquals("30.0", product3.getDescription());
        assertEquals("Category 2", product3.getCategoryId());
       // assertEquals(Arrays.asList("Feature 3", "Feature 4"), product3.getFeatures());
    } */
}