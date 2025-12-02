package edu.sena.petcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.sena.petcare.dto.vaccine.VaccineNewUpdateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.services.vaccine.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<VaccineReadDTO> createVaccine(@Valid @RequestBody VaccineNewUpdateDTO vaccineDTO) {
        VaccineReadDTO created = vaccineService.createVaccine(vaccineDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VaccineReadDTO>> getAllVaccines() {
        List<VaccineReadDTO> vaccines = vaccineService.getAllVaccines();
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VaccineReadDTO>> getVaccineById(@PathVariable Long id) {
        Optional<VaccineReadDTO> vaccine = vaccineService.getVaccineById(id);
        return ResponseEntity.ok(vaccine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineReadDTO> updateVaccine(@PathVariable Long id,
            @Valid @RequestBody VaccineNewUpdateDTO vaccineDTO) {
        VaccineReadDTO updated = vaccineService.updateVaccine(id, vaccineDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return ResponseEntity.noContent().build();
    }
}
