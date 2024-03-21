package com.tga.inventoryservice.adapters.repository;


import com.tga.inventoryservice.domain.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {

    List<InventoryEntity> findBySkuIdIn(List<String> skuId);

    List<InventoryEntity> findByProductIdIn(List<UUID> productId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE inventory inv Set inv.stock_quantity=stock_quantity-? WHERE inv.sku_id = ?", nativeQuery = true)
    int deductQuantity(@Param("quantity") Integer quantity, @Param("skuId") String skuId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE inventory inv Set inv.stock_quantity=stock_quantity+? WHERE inv.sku_id = ?", nativeQuery = true)
    int addQuantity(@Param("quantity") Integer quantity, @Param("skuId") String skuId);

    Optional<InventoryEntity> findBySkuId(UUID skuId);

    @Query(value = "SELECT i FROM inventory i WHERE i.sku_id = ? AND i.product_id = ?", nativeQuery = true)
    Optional<InventoryEntity> findBySkuIdAndProductId(@Param("skuId") String skuId, @Param("productId") String productId);

    @Query(value = "SELECT * FROM inventory i WHERE i.sku_id = ? AND i.product_id = ? AND i.supplier_id=?", nativeQuery = true)
    Optional<InventoryEntity> findBySkuIdProductIdSupplierId(@Param("skuId") String skuId, @Param("productId") String productId, @Param("supplierId") String supplierId);


}
