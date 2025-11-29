package org.example.hospital.controller;

import org.example.hospital.dto.*;
import org.example.hospital.enums.Role;
import org.example.hospital.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/doctor")
    public DoctorDTO createDoctor(@RequestBody DoctorDTO dto) { return service.createDoctor(dto); }

    @PostMapping("/patient")
    public PatientDTO createPatient(@RequestBody PatientDTO dto) { return service.createPatient(dto); }

    @PostMapping("/nurse")
    public NurseDTO createNurse(@RequestBody NurseDTO dto) { return service.createNurse(dto); }

    @PostMapping("/pharmacist")
    public PharmacistDTO createPharmacist(@RequestBody PharmacistDTO dto) { return service.createPharmacist(dto); }

    @PostMapping("/billing-officer")
    public BillingOfficerDTO createBillingOfficer(@RequestBody BillingOfficerDTO dto) { return service.createBillingOfficer(dto); }

    @PostMapping("/lab-technician")
    public LabTechnicianDTO createLabTechnician(@RequestBody LabTechnicianDTO dto) { return service.createLabTechnician(dto); }

    @PutMapping("/doctor/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO dto) { return service.updateDoctor(id, dto); }

    @PutMapping("/patient/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto) { return service.updatePatient(id, dto); }

    @PutMapping("/nurse/{id}")
    public NurseDTO updateNurse(@PathVariable Long id, @RequestBody NurseDTO dto) { return service.updateNurse(id, dto); }

    @PutMapping("/pharmacist/{id}")
    public PharmacistDTO updatePharmacist(@PathVariable Long id, @RequestBody PharmacistDTO dto) { return service.updatePharmacist(id, dto); }

    @PutMapping("/billing-officer/{id}")
    public BillingOfficerDTO updateBillingOfficer(@PathVariable Long id, @RequestBody BillingOfficerDTO dto) { return service.updateBillingOfficer(id, dto); }

    @PutMapping("/lab-technician/{id}")
    public LabTechnicianDTO updateLabTechnician(@PathVariable Long id, @RequestBody LabTechnicianDTO dto) { return service.updateLabTechnician(id, dto); }

    @DeleteMapping("/{role}/{id}")
    public void deactivateUser(@PathVariable Role role, @PathVariable Long id) {
        service.deactivateUser(role, id);
    }
}
