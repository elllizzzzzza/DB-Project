package org.example.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.services.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public ResponseEntity<DrugDTO> createDrug(@RequestBody DrugDTO dto) {
        DrugDTO created = drugService.createDrug(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDTO> getDrug(@PathVariable Long id) {
        DrugDTO drug = drugService.getDrugById(id);
        return ResponseEntity.ok(drug);
    }

    @GetMapping
    public ResponseEntity<List<DrugDTO>> getAllDrugs() {
        List<DrugDTO> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDTO> updateDrug(@PathVariable Long id,
                                              @RequestBody DrugDTO dto) {
        DrugDTO updated = drugService.updateDrug(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }
}
