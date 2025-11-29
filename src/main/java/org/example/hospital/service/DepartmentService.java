package org.example.hospital.service;

import org.example.hospital.converter.DepartmentConverter;
import org.example.hospital.dto.DepartmentDTO;
import org.example.hospital.entity.Department;
import org.example.hospital.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final DepartmentConverter converter;

    public DepartmentService(DepartmentRepository departmentRepo, DepartmentConverter converter) {
        this.departmentRepo = departmentRepo;
        this.converter = converter;
    }

    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department dept = new Department();
        converter.convertToEntity(dto, dept);
        departmentRepo.save(dept);
        return converter.convertToDTO(dept, new DepartmentDTO());
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepo.findAll().stream()
                .map(dept -> converter.convertToDTO(dept, new DepartmentDTO()))
                .toList();
    }

    public void deleteDepartment(Long deptId) {
        departmentRepo.findById(deptId).ifPresent(departmentRepo::delete);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO dto) {
        Department dept = departmentRepo.findById(dto.getDeptId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        converter.convertToEntity(dto, dept);
        return converter.convertToDTO(dept, new DepartmentDTO());
    }
}
