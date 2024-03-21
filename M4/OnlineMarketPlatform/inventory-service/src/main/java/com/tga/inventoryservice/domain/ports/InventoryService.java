package com.tga.inventoryservice.domain.ports;


import com.tga.inventory.model.Inventory;
import com.tga.inventoryservice.adapters.dto.query.CheckInventoryResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    List<Inventory> findAll();

    Inventory findById(final UUID inventoryId);

    Inventory save(final Inventory Inventory);

    List<Inventory> save(List<Inventory> inventories);

    Inventory update(final Inventory Inventory);

    List<Inventory>  update(List<Inventory> inventories);

    Inventory update(final UUID inventoryId, final Inventory categoryDto);


    public List<CheckInventoryResponse> isInStock(List<String> skuCode);
    void deleteById(final UUID userId);

    Inventory addInventory(Inventory inventory);

    public List<Inventory> deductInventories(List<Inventory> inventories);

    public List<Inventory> restoreInventories(List<Inventory> inventories);

    public Inventory deductInventory(Inventory inventory);

}
