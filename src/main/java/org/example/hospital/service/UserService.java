package org.example.hospital.service;

import org.example.hospital.converter.*;
import org.example.hospital.dto.*;
import org.example.hospital.entity.*;
import org.example.hospital.enums.Role;
import org.example.hospital.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final NurseRepository nurseRepo;
    private final PharmacistRepository pharmacistRepo;
    private final BillingOfficerRepository billingOfficerRepo;
    private final LabTechnicianRepository labTechRepo;

    private final DoctorConverter doctorConverter;
    private final PatientConverter patientConverter;
    private final NurseConverter nurseConverter;
    private final PharmacistConverter pharmacistConverter;
    private final BillingOfficerConverter billingOfficerConverter;
    private final LabTechnicianConverter labTechConverter;

    public UserService(
            DoctorRepository doctorRepo,
            PatientRepository patientRepo,
            NurseRepository nurseRepo,
            PharmacistRepository pharmacistRepo,
            BillingOfficerRepository billingOfficerRepo,
            LabTechnicianRepository labTechRepo,
            DoctorConverter doctorConverter,
            PatientConverter patientConverter,
            NurseConverter nurseConverter,
            PharmacistConverter pharmacistConverter,
            BillingOfficerConverter billingOfficerConverter,
            LabTechnicianConverter labTechConverter
    ) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.nurseRepo = nurseRepo;
        this.pharmacistRepo = pharmacistRepo;
        this.billingOfficerRepo = billingOfficerRepo;
        this.labTechRepo = labTechRepo;

        this.doctorConverter = doctorConverter;
        this.patientConverter = patientConverter;
        this.nurseConverter = nurseConverter;
        this.pharmacistConverter = pharmacistConverter;
        this.billingOfficerConverter = billingOfficerConverter;
        this.labTechConverter = labTechConverter;
    }


    public DoctorDTO createDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setRole(Role.DOCTOR);
        doctorConverter.convertToEntity(dto, doctor);
        doctorRepo.save(doctor);
        return doctorConverter.convertToDTO(doctor, new DoctorDTO());
    }

    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setRole(Role.PATIENT);
        patientConverter.convertToEntity(dto, patient);
        patientRepo.save(patient);
        return patientConverter.convertToDTO(patient, new PatientDTO());
    }

    public NurseDTO createNurse(NurseDTO dto) {
        Nurse nurse = new Nurse();
        nurse.setRole(Role.NURSE);
        nurseConverter.convertToEntity(dto, nurse);
        nurseRepo.save(nurse);
        return nurseConverter.convertToDTO(nurse, new NurseDTO());
    }

    public PharmacistDTO createPharmacist(PharmacistDTO dto) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setRole(Role.PHARMACIST);
        pharmacistConverter.convertToEntity(dto, pharmacist);
        pharmacistRepo.save(pharmacist);
        return pharmacistConverter.convertToDTO(pharmacist, new PharmacistDTO());
    }

    public BillingOfficerDTO createBillingOfficer(BillingOfficerDTO dto) {
        BillingOfficer officer = new BillingOfficer();
        officer.setRole(Role.BILLING_OFFICER);
        billingOfficerConverter.convertToEntity(dto, officer);
        billingOfficerRepo.save(officer);
        return billingOfficerConverter.convertToDTO(officer, new BillingOfficerDTO());
    }

    public LabTechnicianDTO createLabTechnician(LabTechnicianDTO dto) {
        LabTechnician tech = new LabTechnician();
        tech.setRole(Role.LAB_TECHNICIAN);
        labTechConverter.convertToEntity(dto, tech);
        labTechRepo.save(tech);
        return labTechConverter.convertToDTO(tech, new LabTechnicianDTO());
    }


    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doctor = doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorConverter.convertToEntity(dto, doctor);
        doctorRepo.save(doctor);
        return doctorConverter.convertToDTO(doctor, new DoctorDTO());
    }

    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientConverter.convertToEntity(dto, patient);
        patientRepo.save(patient);
        return patientConverter.convertToDTO(patient, new PatientDTO());
    }

    public NurseDTO updateNurse(Long id, NurseDTO dto) {
        Nurse nurse = nurseRepo.findById(id).orElseThrow(() -> new RuntimeException("Nurse not found"));
        nurseConverter.convertToEntity(dto, nurse);
        nurseRepo.save(nurse);
        return nurseConverter.convertToDTO(nurse, new NurseDTO());
    }

    public PharmacistDTO updatePharmacist(Long id, PharmacistDTO dto) {
        Pharmacist pharmacist = pharmacistRepo.findById(id).orElseThrow(() -> new RuntimeException("Pharmacist not found"));
        pharmacistConverter.convertToEntity(dto, pharmacist);
        pharmacistRepo.save(pharmacist);
        return pharmacistConverter.convertToDTO(pharmacist, new PharmacistDTO());
    }

    public BillingOfficerDTO updateBillingOfficer(Long id, BillingOfficerDTO dto) {
        BillingOfficer officer = billingOfficerRepo.findById(id).orElseThrow(() -> new RuntimeException("Billing officer not found"));
        billingOfficerConverter.convertToEntity(dto, officer);
        billingOfficerRepo.save(officer);
        return billingOfficerConverter.convertToDTO(officer, new BillingOfficerDTO());
    }

    public LabTechnicianDTO updateLabTechnician(Long id, LabTechnicianDTO dto) {
        LabTechnician tech = labTechRepo.findById(id).orElseThrow(() -> new RuntimeException("Lab technician not found"));
        labTechConverter.convertToEntity(dto, tech);
        labTechRepo.save(tech);
        return labTechConverter.convertToDTO(tech, new LabTechnicianDTO());
    }


    public void deactivateUser(Role role, Long userId) {
        switch (role) {
            case DOCTOR -> doctorRepo.deleteById(userId);
            case PATIENT -> patientRepo.deleteById(userId);
            case NURSE -> nurseRepo.deleteById(userId);
            case PHARMACIST -> pharmacistRepo.deleteById(userId);
            case BILLING_OFFICER -> billingOfficerRepo.deleteById(userId);
            case LAB_TECHNICIAN -> labTechRepo.deleteById(userId);
            default -> throw new RuntimeException("Cannot delete this role");
        }
    }
}
