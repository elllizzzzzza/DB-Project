package org.example.hospital.service;

import org.example.hospital.converter.AppointmentConverter;
import org.example.hospital.converter.ScheduleConverter;
import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.dto.ScheduleDTO;
import org.example.hospital.dto.TimeSlotDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Schedule;
import org.example.hospital.repository.AppointmentRepository;
import org.example.hospital.repository.DoctorRepository;
import org.example.hospital.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepo;
    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;
    private final ScheduleConverter scheduleConverter;
    private final AppointmentConverter appointmentConverter;

    public ScheduleService(ScheduleRepository scheduleRepo,
                           DoctorRepository doctorRepo,
                           AppointmentRepository appointmentRepo,
                           ScheduleConverter scheduleConverter,
                           AppointmentConverter appointmentConverter) {
        this.scheduleRepo = scheduleRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
        this.scheduleConverter = scheduleConverter;
        this.appointmentConverter = appointmentConverter;
    }

    public ScheduleDTO createSchedule(Long doctorId, ScheduleDTO dto) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Schedule schedule = new Schedule();
        schedule.setDoctor(doctor);
        scheduleConverter.convertToEntity(dto, schedule);
        scheduleRepo.save(schedule);
        return scheduleConverter.convertToDTO(schedule, new ScheduleDTO());
    }

    public ScheduleDTO updateSchedule(Long scheduleId, ScheduleDTO dto) {
        Schedule schedule = scheduleRepo.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleConverter.convertToEntity(dto, schedule);
        scheduleRepo.save(schedule);
        return scheduleConverter.convertToDTO(schedule, new ScheduleDTO());
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepo.deleteById(scheduleId);
    }

    public List<ScheduleDTO> getDoctorSchedules(Long doctorId) {
        List<Schedule> schedules = scheduleRepo.findByDoctor_UserId(doctorId);
        return schedules.stream()
                .map(s -> scheduleConverter.convertToDTO(s, new ScheduleDTO()))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsForDoctor(Long doctorId, LocalDate date) {
        List<Appointment> appointments = appointmentRepo.findByDoctor_UserId(doctorId);
        return appointments.stream()
                .filter(a -> a.getStartTime().toLocalDate().equals(date))
                .map(a -> appointmentConverter.convertToDTO(a, new AppointmentDTO()))
                .collect(Collectors.toList());
    }

    public List<TimeSlotDTO> getAvailableSlots(Long doctorId, LocalDate date, int slotMinutes) {
        List<ScheduleDTO> schedules = getDoctorSchedules(doctorId);
        List<AppointmentDTO> appointments = getAppointmentsForDoctor(doctorId, date);

        List<TimeSlotDTO> availableSlots = new ArrayList<>();
        for (ScheduleDTO schedule : schedules) {
            LocalTime start = schedule.getStartTime();
            LocalTime end = schedule.getEndTime();
            LocalTime current = start;

            while (current.plusMinutes(slotMinutes).compareTo(end) <= 0) {
                LocalDateTime slotStart = LocalDateTime.of(date, current);
                LocalDateTime slotEnd = slotStart.plusMinutes(slotMinutes);

                boolean conflict = appointments.stream().anyMatch(a ->
                        a.getStartTime().isBefore(slotEnd) && a.getEndTime().isAfter(slotStart)
                );

                if (!conflict) {
                    availableSlots.add(new TimeSlotDTO(slotStart, slotEnd));
                }
                current = current.plusMinutes(slotMinutes);
            }
        }
        return availableSlots;
    }

}
