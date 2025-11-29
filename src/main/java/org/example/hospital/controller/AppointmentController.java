package org.example.hospital.controller;

import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.dto.TimeSlotDTO;
import org.example.hospital.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto) {
        AppointmentDTO created = appointmentService.createAppointment(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsForDoctor(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}/available-slots")
    public ResponseEntity<List<TimeSlotDTO>> getAvailableSlotsForDoctor(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int slotMinutes) {

        List<TimeSlotDTO> slots = appointmentService.getAvailableSlotsForDoctor(doctorId, date, slotMinutes);
        return ResponseEntity.ok(slots);
    }

}
