package edu.sena.petcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryNewUpdateDTO;
import edu.sena.petcare.services.vaccinationhistory.VaccinationHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vaccination-history")
@RequiredArgsConstructor
public class VaccinationHistoryController {

    private final VaccinationHistoryService service;

    @PostMapping
    public ResponseEntity<VaccinationHistoryReadDTO> create(@Valid @RequestBody VaccinationHistoryNewUpdateDTO dto) {
        VaccinationHistoryReadDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VaccinationHistoryReadDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VaccinationHistoryReadDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationHistoryReadDTO> update(@PathVariable Long id,
            @Valid @RequestBody VaccinationHistoryNewUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<VaccinationHistoryReadDTO>> getByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(service.getByPetId(petId));
    }
}
