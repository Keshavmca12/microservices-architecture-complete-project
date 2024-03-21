Scenario 1: Add a new product to the database

Given a product with name "Test Product" and description "Test Product Desc"
When the user adds the product to the database
Then the product is successfully added with a unique ID
Scenario 2: Load data from an Excel file into the database

Given an Excel file "testdata.xlsx" with product data
When the user loads the data into the database
Then the correct number of products are added to the database with the correct data
Scenario 3: Retrieve all products from the database

Given there are three products in the database
When the user retrieves all products
Then the correct number of products are returned with the correct data

Scenario 4: Retrieve products by name using regular expressions

Given there are three products in the database with names "Product 1", "Product 2", and "Product 3"
When the user retrieves products with name "Product"
Then the correct number of products are returned with names "Product 1", "Product 2", and "Product 3"
Scenario 5: Retrieve products by ID

Given there are three products in the database with IDs "1", "2", and "3"
When the user retrieves products with IDs "1" and "3"
Then the correct products are returned with IDs "1" and "3"
Scenario 6: Retrieve inventory data from the inventory service

Given the inventory service is running
When the user retrieves the inventory data
Then the correct inventory data is returned