package org.example.hospital.services;
import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.AppointmentConverter;
import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Patient;
import org.example.hospital.repositories.AppointmentRepository;
import org.example.hospital.repositories.DoctorRepository;
import org.example.hospital.repositories.PatientRepository;
import org.example.hospital.services.AppointmentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentConverter appointmentConverter;
    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
        Appointment appointment = appointmentConverter.toEntity(dto, doctor, patient);
        Appointment saved = appointmentRepository.save(appointment);
        return appointmentConverter.toDTO(saved);
    }
    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        return appointmentConverter.toDTO(appointment);
    }
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        existing.setStartTime(dto.getAppointmentDate());
        existing.setDuration(dto.getDuration());
        if (dto.getDoctorId() != null &&
                (existing.getDoctor() == null ||
                        !existing.getDoctor().getUserId().equals(dto.getDoctorId()))) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
            existing.setDoctor(doctor);
        }
        if (dto.getPatientId() != null &&
                (existing.getPatient() == null ||
                        !existing.getPatient().getUserId().equals(dto.getPatientId()))) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
            existing.setPatient(patient);
        }
        Appointment updated = appointmentRepository.save(existing);
        return appointmentConverter.toDTO(updated);
    }
    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}