package org.example.hospital.controller;
import lombok.RequiredArgsConstructor;
import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.services.ProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/procedures")
@RequiredArgsConstructor
public class ProcedureController {
    private final ProcedureService procedureService;
    @PostMapping
    public ResponseEntity<ProcedureDTO> createProcedure(@RequestBody ProcedureDTO dto) {
        ProcedureDTO created = procedureService.createProcedure(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDTO> getProcedure(@PathVariable Long id) {
        ProcedureDTO procedure = procedureService.getProcedureById(id);
        return ResponseEntity.ok(procedure);
    }
    @GetMapping
    public ResponseEntity<List<ProcedureDTO>> getAllProcedures() {
        List<ProcedureDTO> procedures = procedureService.getAllProcedures();
        return ResponseEntity.ok(procedures);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDTO> updateProcedure(@PathVariable Long id,
                                                        @RequestBody ProcedureDTO dto) {
        ProcedureDTO updated = procedureService.updateProcedure(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.noContent().build();
    }
}
