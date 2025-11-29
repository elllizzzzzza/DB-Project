package org.example.hospital.service;

import org.example.hospital.converter.ProcedureConverter;
import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Lab;
import org.example.hospital.entity.LabTechnician;
import org.example.hospital.entity.Procedure;
import org.example.hospital.enums.ProcedureResultStatus;
import org.example.hospital.repository.AppointmentRepository;
import org.example.hospital.repository.LabRepository;
import org.example.hospital.repository.LabTechnicianRepository;
import org.example.hospital.repository.ProcedureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureService {

    private final ProcedureRepository procedureRepo;
    private final AppointmentRepository appointmentRepo;
    private final LabRepository labRepo;
    private final LabTechnicianRepository techRepo;
    private final ProcedureConverter procedureConverter;

    public ProcedureService(ProcedureRepository procedureRepo,
                            AppointmentRepository appointmentRepo,
                            LabRepository labRepo,
                            LabTechnicianRepository techRepo,
                            ProcedureConverter procedureConverter) {
        this.procedureRepo = procedureRepo;
        this.appointmentRepo = appointmentRepo;
        this.labRepo = labRepo;
        this.techRepo = techRepo;
        this.procedureConverter = procedureConverter;
    }

    public ProcedureDTO createProcedure(Long appointmentId, ProcedureDTO dto) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Lab lab = labRepo.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found"));

        Procedure procedure = new Procedure();
        procedure.setAppointment(appointment);
        procedure.setLab(lab);
        procedureConverter.convertToEntity(dto, procedure);
        procedureRepo.save(procedure);
        return procedureConverter.convertToDTO(procedure, new ProcedureDTO());
    }

    public ProcedureDTO updateProcedureResult(Long procedureId, String result, ProcedureResultStatus status) {
        Procedure procedure = procedureRepo.findById(procedureId)
                .orElseThrow(() -> new RuntimeException("Procedure not found"));
        procedure.setResult(result);
        procedure.setResultStatus(status);
        procedureRepo.save(procedure);
        return procedureConverter.convertToDTO(procedure, new ProcedureDTO());
    }

    public List<ProcedureDTO> getProceduresByPatient(Long patientId) {
        return procedureRepo.findByAppointment_Patient_UserId(patientId)
                .stream()
                .map(p -> procedureConverter.convertToDTO(p, new ProcedureDTO()))
                .collect(Collectors.toList());
    }

    public List<ProcedureDTO> getProceduresByLab(Long labId) {
        return procedureRepo.findByLab_LabId(labId)
                .stream()
                .map(p -> procedureConverter.convertToDTO(p, new ProcedureDTO()))
                .collect(Collectors.toList());
    }

    public List<ProcedureDTO> getPendingProceduresForTechnician(Long technicianId) {
        LabTechnician tech = techRepo.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found"));
        Lab lab = tech.getLab();
        return procedureRepo.findByLab_LabIdAndResultStatus(lab.getLabId(), ProcedureResultStatus.PENDING)
                .stream()
                .map(p -> procedureConverter.convertToDTO(p, new ProcedureDTO()))
                .collect(Collectors.toList());
    }
}
