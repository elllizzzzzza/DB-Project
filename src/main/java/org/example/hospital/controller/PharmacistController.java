package org.example.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.example.hospital.dto.PharmacistDTO;
import org.example.hospital.services.PharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/pharmacists")
@RequiredArgsConstructor
public class PharmacistController {
    private final PharmacistService pharmacistService;
    @PostMapping
    public ResponseEntity<PharmacistDTO> createPharmacist(@RequestBody PharmacistDTO dto) {
        PharmacistDTO created = pharmacistService.createPharmacist(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PharmacistDTO> getPharmacist(@PathVariable Long id) {
        PharmacistDTO pharmacist = pharmacistService.getPharmacistById(id);
        return ResponseEntity.ok(pharmacist);
    }
    @GetMapping
    public ResponseEntity<List<PharmacistDTO>> getAllPharmacists() {
        List<PharmacistDTO> pharmacists = pharmacistService.getAllPharmacists();
        return ResponseEntity.ok(pharmacists);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PharmacistDTO> updatePharmacist(@PathVariable Long id,
                                                          @RequestBody PharmacistDTO dto) {
        PharmacistDTO updated = pharmacistService.updatePharmacist(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacist(@PathVariable Long id) {
        pharmacistService.deletePharmacist(id);
        return ResponseEntity.noContent().build();
    }
}
