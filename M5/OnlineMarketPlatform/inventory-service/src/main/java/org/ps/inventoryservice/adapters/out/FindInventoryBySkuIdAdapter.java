package org.ps.inventoryservice.adapters.out;


import lombok.RequiredArgsConstructor;
import org.ps.inventoryservice.adapters.out.repository.InventoryRepository;
import org.ps.inventoryservice.adapters.out.repository.entity.InventoryEntity;
import org.ps.inventoryservice.adapters.out.repository.mapper.InventoryMappingHelper;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.out.FindInventoryBySkuIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindInventoryBySkuIdAdapter implements FindInventoryBySkuIdOutputPort {
    private final InventoryRepository inventoryRepository;

    @Override
    public Optional<Inventory> execute(final String skuId) {
        final Optional<InventoryEntity> inventoryEntity = this.inventoryRepository.findBySkuId(skuId);
        return inventoryEntity.map(InventoryMappingHelper::map);
    }
}
