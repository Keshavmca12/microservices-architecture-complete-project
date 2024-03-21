package org.ps.inventoryservice.application.core.usecase;

import lombok.AllArgsConstructor;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.in.FindInventoriesInputPort;
import org.ps.inventoryservice.application.ports.out.FindInventoriesOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindInventoriesUseCase implements FindInventoriesInputPort {
    final FindInventoriesOutputPort findInventoriesOutputPort;

    @Override
    public List<Inventory> execute() {
        return findInventoriesOutputPort.execute();
    }
}
