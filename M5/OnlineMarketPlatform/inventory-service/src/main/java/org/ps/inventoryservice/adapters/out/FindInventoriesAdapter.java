package org.ps.inventoryservice.adapters.out;


import lombok.RequiredArgsConstructor;
import org.ps.inventoryservice.adapters.out.repository.InventoryRepository;
import org.ps.inventoryservice.adapters.out.repository.entity.InventoryEntity;
import org.ps.inventoryservice.adapters.out.repository.mapper.InventoryMappingHelper;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.out.FindInventoriesOutputPort;
import org.ps.inventoryservice.application.ports.out.FindInventoryBySkuIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindInventoriesAdapter implements FindInventoriesOutputPort {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> execute() {
        final List<InventoryEntity> inventoryEntities = this.inventoryRepository.findAll();
        return inventoryEntities.stream().map(inventoryEntity -> {
            return InventoryMappingHelper.map(inventoryEntity);
        }).collect(Collectors.toList());

    }
}
