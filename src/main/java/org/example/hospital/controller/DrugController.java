package org.example.hospital.controller;

import org.example.hospital.dto.DrugDTO;
import org.example.hospital.service.DrugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    private final DrugService service;

    public DrugController(DrugService service) {
        this.service = service;
    }

    @PostMapping
    public DrugDTO addDrug(@RequestBody DrugDTO dto) {
        return service.addDrug(dto);
    }

    @GetMapping("/available")
    public List<DrugDTO> getAvailableDrugs() {
        return service.getAvailableDrugs();
    }

    @PutMapping("/{drugId}/out-of-stock")
    public void markOutOfStock(@PathVariable Long drugId) {
        service.markOutOfStock(drugId);
    }
}
