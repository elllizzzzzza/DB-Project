package org.example.hospital.service;

import org.example.hospital.converter.LabConverter;
import org.example.hospital.dto.LabDTO;
import org.example.hospital.entity.Lab;
import org.example.hospital.entity.LabTechnician;
import org.example.hospital.repository.LabRepository;
import org.example.hospital.repository.LabTechnicianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabService {

    private final LabRepository labRepo;
    private final LabTechnicianRepository techRepo;
    private final LabConverter converter;

    public LabService(LabRepository labRepo, LabTechnicianRepository techRepo, LabConverter converter) {
        this.labRepo = labRepo;
        this.techRepo = techRepo;
        this.converter = converter;
    }

    public LabDTO createLab(LabDTO dto) {
        Lab lab = new Lab();
        converter.convertToEntity(dto, lab);
        labRepo.save(lab);
        return converter.convertToDTO(lab, new LabDTO());
    }

    public void assignTechnician(Long labId, Long techId) {
        Lab lab = labRepo.findById(labId).orElseThrow(() -> new RuntimeException("Lab not found"));
        LabTechnician tech = techRepo.findById(techId).orElseThrow(() -> new RuntimeException("Technician not found"));
        tech.setLab(lab);
        techRepo.save(tech);
    }

    public List<LabDTO> getAllLabs() {
        return labRepo.findAll().stream()
                .map(lab -> converter.convertToDTO(lab, new LabDTO()))
                .toList();
    }
}
