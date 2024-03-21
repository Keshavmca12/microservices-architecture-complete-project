package org.ps.inventoryservice.adapters.in.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ps.inventoryservice.adapters.out.FindInventoriesAdapter;
import org.ps.inventoryservice.application.core.domain.Inventory;
import org.ps.inventoryservice.application.ports.in.FindInventoriesInputPort;
import org.ps.inventoryservice.application.ports.in.FindInventoryBySkuIdInputPort;
import org.ps.inventoryservice.application.ports.in.SaveInventoryInputPort;
import org.ps.inventoryservice.application.ports.out.SaveInventoryOutputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    final FindInventoriesInputPort findInventoriesInputPort;
    final SaveInventoryInputPort saveInventoryInputPort;
    final FindInventoryBySkuIdInputPort findInventoryBySkuIdInputPort;
    @GetMapping
    public ResponseEntity<List<Inventory>> findAll() {
        log.info("*** Inventories List *");
        return ResponseEntity.ok(this.findInventoriesInputPort.execute());
    }

    @PostMapping
    public void save(@RequestBody @NotNull(message = "Input must not NULL")
                     @Valid final Inventory inventory) {
        log.info("*** Save Inventory *");
        saveInventoryInputPort.execute(inventory);
    }
    @GetMapping("/{sku}")
    public ResponseEntity<Inventory> findBySku(@PathVariable("sku")
                                                   @NotBlank(message = "Input must not blank") @Valid final String sku) {
        log.info("*** Inventory Details By sku *");
        return ResponseEntity.ok(this.findInventoryBySkuIdInputPort.execute(sku));
    }


}
