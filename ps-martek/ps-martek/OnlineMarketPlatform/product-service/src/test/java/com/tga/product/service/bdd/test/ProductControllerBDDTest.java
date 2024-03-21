package com.tga.product.service.bdd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.entity.Product;
import com.tga.product.service.repository.ProductRepository;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = { "pretty", "json:target/cucumber-report.json" })
public class ProductControllerBDDTest {
	private ProductDTO productDTO;
	private List<String> productIds = new ArrayList<>();
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	String responseContent;

	@Given("a product with name {string} and description {string}")
	public void aProductWithNameAndPrice(String name, String description) {
		productDTO = new ProductDTO();
		productDTO.setProductName(name);
		productDTO.setDescription(description);
	}

	@When("the user adds the product to the database")
	public void theUserAddsTheProductToTheDatabase() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/product/product")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDTO)))
				.andExpect(status().isOk()).andReturn();
		String productId = result.getResponse().getContentAsString();
		productIds.add(productId);
	}

	@Then("the product is successfully added with a unique ID")
	public void theProductIsSuccessfullyAddedWithAUniqueID() {
		assertEquals(1, productIds.size());
		Product product = productRepository.findById(productIds.get(0)).orElse(null);
		assertNotNull(product);
		assertEquals(productDTO.getProductName(), product.getProductName());
		assertEquals(productDTO.getDescription(), product.getDescription());
	}

	@Given("an Excel file {string} with product data")
	public void anExcelFileWithProductData(String fileName) throws Exception {
		File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
		Path path = Paths.get("C:/temp/" + fileName);

		byte[] fileContent = Files.readAllBytes(path);

		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName, "application/vnd.ms-excel",
				fileContent);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.multipart("/product/loadData").file(mockMultipartFile))
				.andExpect(status().isOk()).andReturn();
		responseContent = result.getResponse().getContentAsString();
	}

	@When("the user loads the data into the database")
	public void theUserLoadsTheDataIntoTheDatabase() throws IOException {
		List<String> productIds = objectMapper.readValue(responseContent, new TypeReference<List<String>>() {
		});
		assertEquals(3, productIds.size());
		this.productIds.addAll(productIds);
	}

	@Then("the correct number of products are added to the database with the correct data")
	public void theCorrectNumberOfProductsAreAddedToTheDatabaseWithTheCorrectData() {
		List<Product> products = productRepository.findAll();
		assertEquals(3, products.size());

		Product product1 = products.stream().filter(p -> p.getId().equals(productIds.get(0))).findFirst().orElse(null);
		assertNotNull(product1);
		assertEquals("Product 1", product1.getProductName());
		assertEquals("10.0", product1.getDescription());
		assertEquals("Category 1", product1.getCategoryId());
		// assertEquals(Arrays.asList("Feature 1", "Feature 2"),
		// product1.getFeatures());

		Product product2 = products.stream().filter(p -> p.getId().equals(productIds.get(1))).findFirst().orElse(null);
		assertNotNull(product2);
		assertEquals("Product 2", product2.getProductName());
		assertEquals("20.0", product2.getDescription());
		assertEquals("Category 1", product2.getCategoryId());
		// assertEquals(Arrays.asList("Feature 2", "Feature 3"),
		// product2.getFeatures());

		Product product3 = products.stream().filter(p -> p.getId().equals(productIds.get(2))).findFirst().orElse(null);
		assertNotNull(product3);
		assertEquals("Product 3", product3.getProductName());
		assertEquals("30.0", product3.getDescription());
		assertEquals("Category 3", product3.getCategoryId());
		// assertEquals(Arrays.asList("Feature 3", "Feature 4"),
		// product3.getFeatures());
	}

}
