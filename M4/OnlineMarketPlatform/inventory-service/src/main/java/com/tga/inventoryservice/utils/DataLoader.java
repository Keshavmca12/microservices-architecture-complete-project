//package com.tga.inventoryservice.utils;
//
//import com.tga.inventoryservice.adapters.repository.InventoryRepository;
//import com.tga.inventoryservice.domain.entities.InventoryEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.UUID;
//
//@SpringBootApplication
//public class DataLoader {
//    private final InventoryRepository inventoryRepository;
//
//    public DataLoader(InventoryRepository inventoryRepository) {
//        this.inventoryRepository = inventoryRepository;
//    }
//
//    public static void main(String... args) throws Exception {
//        InventoryEntity inventory1 = new InventoryEntity();
//        inventory1.setInventoryName("Inv 1");
//        inventory1.setSkuId(UUID.randomUUID().toString());
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
//        inventory2.setQuantity(200);
//        inventory2.setProductId(UUID.randomUUID().toString());
//        inventory2.setPriceUnit(220.00);
//
//        inventoryRepository.save(inventory2);
////        InventoryEntity inventory3 = new InventoryEntity();
////
////        inventory2 = new InventoryEntity();
////        inventory2.setInventoryName("Inv 2");
////        inventory2.setSkuId(inventory1.getSkuId());
////        inventory2.setQuantity(100);
////        inventory2.setProductId(UUID.fromString(UUID.randomUUID().toString().substring(0, 15)));
////        inventory2.setPriceUnit(200.50);
//
//    }
//}
