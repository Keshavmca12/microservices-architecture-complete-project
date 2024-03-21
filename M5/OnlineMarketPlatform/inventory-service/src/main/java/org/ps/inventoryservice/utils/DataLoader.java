package org.ps.inventoryservice.utils;


import lombok.RequiredArgsConstructor;
import org.ps.inventoryservice.adapters.out.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
//        InventoryEntity inventory1 = new InventoryEntity();
//        inventory1.setInventoryName("Inv 1");
        //inventory1.setSkuId(UUID.randomUUID().toString());
//        inventory1.setQuantity(100);
//        inventory1.setProductId(UUID.randomUUID().toString());
//        inventory1.setPriceUnit(200.50);
//        inventory1.setSupplierId(UUID.randomUUID().toString());
//        inventoryRepository.save(inventory1);
//        InventoryEntity inventory2;
//
//        inventory2 = new InventoryEntity();
//        inventory2.setInventoryName("Inv 2");
//        inventory2.setSkuId(UUID.randomUUID().toString());
//        inventory2.setQuantity(1000);
//        inventory2.setProductId(UUID.randomUUID().toString());
//        inventory2.setPriceUnit(220.00);
//
//        inventoryRepository.save(inventory2);
//
//        inventory2 = new InventoryEntity();
//        inventory2.setInventoryName("Inv 3");
//        inventory2.setSkuId(UUID.randomUUID().toString());
//        inventory2.setQuantity(400);
//        inventory2.setProductId(UUID.randomUUID().toString());
//        inventory2.setPriceUnit(500.00);
//        inventoryRepository.save(inventory2);

    }
}
