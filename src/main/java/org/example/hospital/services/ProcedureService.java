package org.example.hospital.services;
import org.example.hospital.dto.ProcedureDTO;
import java.util.List;
public interface ProcedureService {
    ProcedureDTO createProcedure(ProcedureDTO dto);
    ProcedureDTO getProcedureById(Long id);
    List<ProcedureDTO> getAllProcedures();
    ProcedureDTO updateProcedure(Long id, ProcedureDTO dto);
    void deleteProcedure(Long id);
}
