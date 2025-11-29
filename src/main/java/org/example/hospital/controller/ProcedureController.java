package org.example.hospital.controller;

import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.enums.ProcedureResultStatus;
import org.example.hospital.service.ProcedureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

    private final ProcedureService service;

    public ProcedureController(ProcedureService service) {
        this.service = service;
    }

    @PostMapping("/appointment/{appointmentId}")
    public ProcedureDTO createProcedure(@PathVariable Long appointmentId, @RequestBody ProcedureDTO dto) {
        return service.createProcedure(appointmentId, dto);
    }

    @PutMapping("/{procedureId}/result")
    public ProcedureDTO updateProcedureResult(@PathVariable Long procedureId,
                                              @RequestParam String result,
                                              @RequestParam ProcedureResultStatus status) {
        return service.updateProcedureResult(procedureId, result, status);
    }

    @GetMapping("/patient/{patientId}")
    public List<ProcedureDTO> getProceduresByPatient(@PathVariable Long patientId) {
        return service.getProceduresByPatient(patientId);
    }

    @GetMapping("/lab/{labId}")
    public List<ProcedureDTO> getProceduresByLab(@PathVariable Long labId) {
        return service.getProceduresByLab(labId);
    }

    @GetMapping("/technician/{techId}/pending")
    public List<ProcedureDTO> getPendingProceduresForTechnician(@PathVariable Long techId) {
        return service.getPendingProceduresForTechnician(techId);
    }
}
