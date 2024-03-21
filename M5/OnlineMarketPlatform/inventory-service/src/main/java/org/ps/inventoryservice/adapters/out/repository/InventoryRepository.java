package org.ps.inventoryservice.adapters.out.repository;


import org.ps.inventoryservice.adapters.out.repository.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, String> {

    Optional<InventoryEntity> findBySkuId(String skuId);

    @Transactional
    @Modifying
    @Query(value="update inventory I set I.stock_quantity = :quantity where I.inventory_id = :inventoryId", nativeQuery = true)
    void updateInventory(@Param("quantity")Integer quantity, @Param("inventoryId")String inventoryId);


}
