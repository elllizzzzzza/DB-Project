package org.example.hospital.service;

import org.example.hospital.converter.ProcedureConverter;
import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.entity.LabTechnician;
import org.example.hospital.entity.Procedure;
import org.example.hospital.repository.LabTechnicianRepository;
import org.example.hospital.repository.ProcedureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabTechnicianService {

    private final ProcedureRepository procedureRepo;
    private final LabTechnicianRepository techRepo;
    private final ProcedureConverter converter;

    public LabTechnicianService(ProcedureRepository procedureRepo, LabTechnicianRepository techRepo,
                                ProcedureConverter converter) {
        this.procedureRepo = procedureRepo;
        this.techRepo = techRepo;
        this.converter = converter;
    }

    public List<ProcedureDTO> viewPendingTests(Long technicianId) {
        LabTechnician tech = techRepo.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found"));
        return procedureRepo.findByLab_LabId(tech.getLab().getLabId()).stream()
                .filter(p -> p.getResultStatus() == org.example.hospital.enums.ProcedureResultStatus.PENDING)
                .map(p -> converter.convertToDTO(p, new ProcedureDTO()))
                .toList();
    }

    public ProcedureDTO recordTestResult(Long procedureId, String result, org.example.hospital.enums.ProcedureResultStatus status) {
        Procedure procedure = procedureRepo.findById(procedureId)
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        procedure.setResult(result);
        procedure.setResultStatus(status);

        procedureRepo.save(procedure);
        return converter.convertToDTO(procedure, new ProcedureDTO());
    }
}
