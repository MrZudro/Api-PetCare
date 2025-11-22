package edu.sena.petcare.controller;

import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import edu.sena.petcare.services.vaccinationhistory_temp.VaccinationHistoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccinationHistoryController {

    private final VaccinationHistoryService service;

    public VaccinationHistoryController(VaccinationHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VaccinationHistoryReadDTO> create(@RequestBody VaccinationHistoryCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationHistoryReadDTO> update(@PathVariable Long id,
            @RequestBody VaccinationHistoryUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinationHistoryReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VaccinationHistoryReadDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
