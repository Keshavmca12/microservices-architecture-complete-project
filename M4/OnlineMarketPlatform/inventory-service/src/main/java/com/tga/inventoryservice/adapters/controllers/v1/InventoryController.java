package com.tga.inventoryservice.adapters.controllers.v1;

import com.tga.inventory.event.InventoryAddedEvent;
import com.tga.inventory.event.InventoryDeductedEvent;
import com.tga.inventory.event.InventoryRestoreEvent;
import com.tga.inventory.model.Inventory;
import com.tga.inventoryservice.adapters.dto.collection.DtoCollectionResponse;
import com.tga.inventoryservice.adapters.dto.query.CheckInventoryResponse;
import com.tga.inventoryservice.adapters.events.pubsub.InventoryEventPublisher;
import com.tga.inventoryservice.domain.ports.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    private final InventoryService inventoryService;


    @GetMapping
    public ResponseEntity<DtoCollectionResponse<Inventory>> findAll() {
        log.info("*** Inventories List *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.inventoryService.findAll()));
    }


    @PostMapping
    public ResponseEntity <DtoCollectionResponse<Inventory>>  save(@RequestBody @NotNull(message = "Input must not NULL")
                                          @Valid final InventoryAddedEvent event) {
        log.info("*** Save Inventory *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.inventoryService.save(event.getInventoryList())));
    }

    /**
     * Deprecated
     * @param inventoryDto
     * @return
     */
    @PutMapping
    public ResponseEntity<Inventory> update(@RequestBody
                                            @NotNull(message = "Input must not NULL")
                                            @Valid final Inventory inventoryDto) {
        log.info("*** Update Inventory *");
        return ResponseEntity.ok(this.inventoryService.update(inventoryDto));
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<Inventory> update(@PathVariable("inventoryId")
                                            @NotBlank(message = "Input must not blank") final UUID inventoryId,
                                            @RequestBody
                                            @NotNull(message = "Input must not NULL")
                                            @Valid final Inventory inventoryDto) {
        log.info("*** update inventory with inventoryId *");
        return ResponseEntity.ok(this.inventoryService.update(inventoryId, inventoryDto));
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("inventoryId") @NotBlank(message = "Input must not blank") @Valid final UUID inventoryId) {
        log.info("*** Delete Inventory by Id*");
        this.inventoryService.deleteById(inventoryId);
        return ResponseEntity.ok(true);
    }


    @GetMapping("/isInStock")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckInventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

    //todo added for testing.will remove once testing is done


}
