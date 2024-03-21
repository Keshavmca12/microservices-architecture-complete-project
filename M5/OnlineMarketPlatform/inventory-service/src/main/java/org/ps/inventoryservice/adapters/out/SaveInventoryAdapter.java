package org.ps.inventoryservice.adapters.out;


import lombok.RequiredArgsConstructor;
import org.ps.inventoryservice.adapters.out.repository.InventoryRepository;
import org.ps.inventoryservice.adapters.out.repository.entity.InventoryEntity;
import org.ps.inventoryservice.adapters.out.repository.mapper.InventoryMappingHelper;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.out.SaveInventoryOutputPort;
import org.ps.inventoryservice.application.ports.out.UpdateInventoryOutputPort;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class SaveInventoryAdapter implements SaveInventoryOutputPort {

    private final InventoryRepository inventoryRepository;

    @Override
    public void execute(final Inventory inventory) {
        final InventoryEntity inventoryEntity = InventoryMappingHelper.map(inventory);
        inventoryEntity.setCreatedAt(Instant.now());
        this.inventoryRepository.save(inventoryEntity);
    }
}
