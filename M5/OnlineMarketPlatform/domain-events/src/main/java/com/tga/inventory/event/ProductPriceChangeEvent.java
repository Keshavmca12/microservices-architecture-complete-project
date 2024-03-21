package com.tga.inventory.event;

import com.tga.inventory.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductPriceChangeEvent implements InventoryEvent {
    private List<Inventory> inventoryList;

}
