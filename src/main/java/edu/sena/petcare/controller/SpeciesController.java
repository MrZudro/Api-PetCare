package edu.sena.petcare.controller;


import edu.sena.petcare.dto.species.SpeciesNewUpdateDTO;
import edu.sena.petcare.dto.species.SpeciesReadDTO;
import edu.sena.petcare.services.species.SpeciesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;

    @PostMapping
    public ResponseEntity<SpeciesReadDTO> create(@Valid @RequestBody SpeciesNewUpdateDTO dto) {
        return new ResponseEntity<>(speciesService.create(dto), HttpStatus.CREATED);
    }

    // Obtener todas las activas
    @GetMapping
    public ResponseEntity<List<SpeciesReadDTO>> getAllActive() {
        return ResponseEntity.ok(speciesService.findAllActive());
    }

    // Obtener por ID activo
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesReadDTO> getByIdActive(@PathVariable Long id) {
        return ResponseEntity.ok(speciesService.findByIdActive(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeciesReadDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody SpeciesNewUpdateDTO dto) {
        return ResponseEntity.ok(speciesService.update(id, dto));
    }

    // Desactivar (Eliminación Lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        speciesService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}