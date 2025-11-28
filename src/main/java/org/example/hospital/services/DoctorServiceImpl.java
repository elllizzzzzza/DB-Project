package org.example.hospital.services;

import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.DoctorConverter;
import org.example.hospital.dto.DoctorDTO;
import org.example.hospital.entity.Department;
import org.example.hospital.entity.Doctor;
import org.example.hospital.enums.Role;
import org.example.hospital.repositories.DepartmentRepository;
import org.example.hospital.repositories.DoctorRepository;
import org.example.hospital.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorConverter doctorConverter;

    @Override
    public DoctorDTO createDoctor(DoctorDTO dto) {
        Department department = null;
        if (dto.getDepartmentId() != null) {
            department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
        }
        Doctor doctor = doctorConverter.toEntity(dto, department);
        if (doctor.getRole() == null) {
            doctor.setRole(Role.DOCTOR);
        }
        Doctor saved = doctorRepository.save(doctor);
        return doctorConverter.toDTO(saved);
    }
    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        return doctorConverter.toDTO(doctor);
    }
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNum(dto.getPhoneNum());


        existing.setInfo(dto.getInfo());
        existing.setLicenseNum(dto.getLicenseNumber());
        existing.setRoomNum(dto.getRoomNumber());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
            existing.setDepartment(department);
        } else {
            existing.setDepartment(null);
        }

        Doctor updated = doctorRepository.save(existing);
        return doctorConverter.toDTO(updated);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
