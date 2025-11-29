package org.example.hospital.service;

import org.example.hospital.converter.AppointmentConverter;
import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.dto.TimeSlotDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Patient;
import org.example.hospital.repository.AppointmentRepository;
import org.example.hospital.repository.DoctorRepository;
import org.example.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository repo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final AppointmentConverter converter;
    private final ScheduleService scheduleService;

    public AppointmentService(AppointmentRepository repo, DoctorRepository doctorRepo,
                              PatientRepository patientRepo, AppointmentConverter converter,
                              ScheduleService scheduleService) {
        this.repo = repo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.converter = converter;
        this.scheduleService = scheduleService;
    }

    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        boolean conflictDoctor = repo.findByDoctor_UserId(doctor.getUserId()).stream()
                .anyMatch(a -> a.getStartTime().isBefore(dto.getEndTime()) && a.getEndTime().isAfter(dto.getStartTime()));
        if (conflictDoctor) throw new RuntimeException("Doctor not available at this time");

        boolean conflictPatient = repo.findByPatient_UserId(patient.getUserId()).stream()
                .anyMatch(a -> a.getStartTime().isBefore(dto.getEndTime()) && a.getEndTime().isAfter(dto.getStartTime()));
        if (conflictPatient) throw new RuntimeException("Patient has overlapping appointment");

        Appointment appointment = new Appointment();
        converter.convertToEntity(dto, appointment);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDuration((int) Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes());

        repo.save(appointment);
        return converter.convertToDTO(appointment, new AppointmentDTO());
    }

    public void cancelAppointment(Long apptId) {
        repo.findById(apptId).ifPresent(a -> {
            a.setStatus("CANCELED");
            repo.save(a);
        });
    }

    public List<AppointmentDTO> getAppointmentsForDoctor(Long doctorId) {
        return repo.findByDoctor_UserId(doctorId).stream()
                .map(a -> converter.convertToDTO(a, new AppointmentDTO()))
                .toList();
    }

    public List<TimeSlotDTO> getAvailableSlotsForDoctor(Long doctorId, LocalDate date, int slotMinutes) {
        return scheduleService.getAvailableSlots(doctorId, date, slotMinutes);
    }

}
