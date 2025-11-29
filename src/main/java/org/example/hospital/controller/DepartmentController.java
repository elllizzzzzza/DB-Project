package org.example.hospital.controller;

import org.example.hospital.dto.DepartmentDTO;
import org.example.hospital.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO dto) {
        return service.createDepartment(dto);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return service.getAllDepartments();
    }

    @PutMapping("/{deptId}")
    public DepartmentDTO updateDepartment(@PathVariable Long deptId, @RequestBody DepartmentDTO dto) {
        dto.setDeptId(deptId);
        return service.updateDepartment(dto);
    }

    @DeleteMapping("/{deptId}")
    public void deleteDepartment(@PathVariable Long deptId) {
        service.deleteDepartment(deptId);
    }
}
