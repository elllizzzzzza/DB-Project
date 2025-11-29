package org.example.hospital.controller;

import org.example.hospital.dto.ScheduleDTO;
import org.example.hospital.dto.TimeSlotDTO;
import org.example.hospital.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<ScheduleDTO> createSchedule(@PathVariable Long doctorId,
                                                      @RequestBody ScheduleDTO dto) {
        ScheduleDTO schedule = scheduleService.createSchedule(doctorId, dto);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long scheduleId,
                                                      @RequestBody ScheduleDTO dto) {
        ScheduleDTO updated = scheduleService.updateSchedule(scheduleId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ScheduleDTO>> getDoctorSchedules(@PathVariable Long doctorId) {
        List<ScheduleDTO> schedules = scheduleService.getDoctorSchedules(doctorId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/doctor/{doctorId}/available-slots")
    public ResponseEntity<List<TimeSlotDTO>> getAvailableSlots(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam int slotMinutes) {

        List<TimeSlotDTO> slots = scheduleService.getAvailableSlots(doctorId, date, slotMinutes);
        return ResponseEntity.ok(slots);
    }
}
