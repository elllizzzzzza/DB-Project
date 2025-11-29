package org.example.hospital.controller;

import org.example.hospital.dto.BillDTO;
import org.example.hospital.service.BillingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    @PostMapping
    public BillDTO createBill(@RequestBody BillDTO dto) {
        return service.createBill(dto);
    }

    @PutMapping("/{billId}/pay")
    public void markPaid(@PathVariable Long billId) {
        service.markPaid(billId);
    }

    @GetMapping("/unpaid")
    public List<BillDTO> getUnpaidBills() {
        return service.getUnpaidBills();
    }
}
