package org.example.hospital.controller;

import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.enums.ProcedureResultStatus;
import org.example.hospital.service.LabTechnicianService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-technicians")
public class LabTechnicianController {

    private final LabTechnicianService service;

    public LabTechnicianController(LabTechnicianService service) {
        this.service = service;
    }

    @GetMapping("/{techId}/pending-tests")
    public List<ProcedureDTO> viewPendingTests(@PathVariable Long techId) {
        return service.viewPendingTests(techId);
    }

    @PutMapping("/procedures/{procedureId}/record-result")
    public ProcedureDTO recordTestResult(@PathVariable Long procedureId,
                                         @RequestParam String result,
                                         @RequestParam ProcedureResultStatus status) {
        return service.recordTestResult(procedureId, result, status);
    }
}
