package edu.sena.petcare.controller;



import edu.sena.petcare.dto.pet.PetNewUpdateDTO;
import edu.sena.petcare.dto.pet.PetReadDTO;
import edu.sena.petcare.services.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetReadDTO> create(@Valid @RequestBody PetNewUpdateDTO dto) {
        return new ResponseEntity<>(petService.create(dto), HttpStatus.CREATED);
    }

    // Obtener todas las activas
    @GetMapping
    public ResponseEntity<List<PetReadDTO>> getAllActive() {
        return ResponseEntity.ok(petService.findAllActive());
    }

    // Obtener por ID activo
    @GetMapping("/{id}")
    public ResponseEntity<PetReadDTO> getByIdActive(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findByIdActive(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetReadDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PetNewUpdateDTO dto) {
        return ResponseEntity.ok(petService.update(id, dto));
    }

    // Desactivar (Eliminación Lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        petService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}