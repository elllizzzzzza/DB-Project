package org.example.hospital.controller;

import org.example.hospital.dto.LabDTO;
import org.example.hospital.service.LabService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
public class LabController {

    private final LabService service;

    public LabController(LabService service) {
        this.service = service;
    }

    @PostMapping
    public LabDTO createLab(@RequestBody LabDTO dto) {
        return service.createLab(dto);
    }

    @PutMapping("/{labId}/assign-technician/{techId}")
    public void assignTechnician(@PathVariable Long labId, @PathVariable Long techId) {
        service.assignTechnician(labId, techId);
    }

    @GetMapping
    public List<LabDTO> getAllLabs() {
        return service.getAllLabs();
    }
}
