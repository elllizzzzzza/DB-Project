package org.example.hospital.controller;

import org.example.hospital.dto.BillDTO;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.service.ReportingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final ReportingService service;

    public ReportingController(ReportingService service) {
        this.service = service;
    }

    // 1. Get appointments within a date range (include doctor & patient names)
    @GetMapping("/appointments")
    public List<AppointmentDTO> getAppointments(@RequestParam String startDate,
                                                @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return service.getAppointments(start, end)
                .stream()
                .map(a -> {
                    AppointmentDTO dto = new AppointmentDTO();
                    dto.setAppointmentId(a.getApptId());
                    dto.setDoctorId(a.getDoctor().getUserId());
                    dto.setDoctorName(a.getDoctor().getName());
                    dto.setPatientId(a.getPatient().getUserId());
                    dto.setPatientName(a.getPatient().getName());
                    dto.setStartTime(a.getStartTime());
                    dto.setEndTime(a.getEndTime());
                    dto.setStatus(a.getStatus());
                    return dto;
                })
                .toList();
    }

    // 2. Most active doctors (by number of appointments)
    @GetMapping("/doctors/most-active")
    public Map<String, Long> mostActiveDoctors() {
        return service.mostActiveDoctors();
    }

    // 3. Most dispensed drugs
    @GetMapping("/drugs/most-dispensed")
    public Map<String, Long> mostDispensedDrugs() {
        return service.mostDispensedDrugs();
    }

    // 4. Out-of-stock drugs
    @GetMapping("/drugs/out-of-stock")
    public List<DrugDTO> outOfStockDrugs() {
        return service.outOfStockDrugs();
    }

    // 5. Unpaid bills
    @GetMapping("/bills/unpaid")
    public List<BillDTO> unpaidBills() {
        return service.unpaidBills();
    }
}
