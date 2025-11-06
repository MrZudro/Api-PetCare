package edu.sena.petcare.controller;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;
import edu.sena.petcare.services.consultation.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultations") // Endpoint principal
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    /**
     * @Documented POST /consultations
     * Crea un nuevo registro de consulta. (Única operación de escritura)
     * @param dto DTO con los datos de la consulta.
     * @return 201 CREATED con la consulta creada.
     */
    @PostMapping
    public ResponseEntity<ConsultationReadDTO> createConsultation(@Valid @RequestBody ConsultationNewDTO dto) {
        ConsultationReadDTO createdConsultation = consultationService.createConsultation(dto);
        return new ResponseEntity<>(createdConsultation, HttpStatus.CREATED);
    }

    /**
     * @Documented GET /consultations
     * Obtiene el listado de todas las consultas (Solo para administradores/reportes).
     * @return 200 OK con la lista de consultas.
     */
    @GetMapping
    public ResponseEntity<List<ConsultationReadDTO>> getAllConsultations() {
        List<ConsultationReadDTO> consultations = consultationService.getAllConsultations();
        return ResponseEntity.ok(consultations);
    }

    /**
     * @Documented GET /consultations/{id}
     * Obtiene una consulta específica por su ID.
     * @param id ID de la consulta.
     * @return 200 OK con los detalles de la consulta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationReadDTO> getConsultationById(@PathVariable Long id) {
        ConsultationReadDTO consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }
    
    /**
     * @Documented GET /consultations/history/{petId}
     * Obtiene el historial de consultas de una mascota.
     * @param petId ID de la mascota.
     * @return 200 OK con la lista de consultas de la mascota.
     */
    @GetMapping("/history/{petId}")
    public ResponseEntity<List<ConsultationReadDTO>> getConsultationHistoryByPet(@PathVariable Long petId) {
        List<ConsultationReadDTO> history = consultationService.getConsultationHistoryByPet(petId);
        return ResponseEntity.ok(history);
    }
    
    // NOTA: Se omiten los métodos PUT y DELETE intencionalmente según el requisito.
}