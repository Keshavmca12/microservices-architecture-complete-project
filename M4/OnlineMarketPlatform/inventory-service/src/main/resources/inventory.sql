use inventorydb;
CREATE TABLE inventory (
  inventory_id binary(16) NOT NULL PRIMARY KEY,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  inventory_name varchar(255),
  price_unit decimal(10,0) ,
  product_id varchar(255) NOT NULL,
  stock_quantity int,
  sku_id varchar(255) NOT NULL,
  supplier_id varchar(255) DEFAULT NULL,
  UNIQUE KEY product_id (product_id),
  UNIQUE KEY sku_id (sku_id)
)