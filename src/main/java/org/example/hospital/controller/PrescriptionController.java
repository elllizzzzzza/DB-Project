package org.example.hospital.controller;

import org.example.hospital.dto.PrescriptionDTO;
import org.example.hospital.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @PostMapping
    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO dto) {
        return service.createPrescription(dto);
    }

    @GetMapping("/patient/{patientId}")
    public List<PrescriptionDTO> getPrescriptionsForPatient(@PathVariable Long patientId) {
        return service.getPrescriptionsForPatient(patientId);
    }
}
