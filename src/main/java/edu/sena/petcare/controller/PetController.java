package edu.sena.petcare.controller;

import edu.sena.petcare.dto.Pet.*;
import edu.sena.petcare.services.pet_temp.PetService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService service;

    @PostMapping
    public ResponseEntity<PetReadDTO> create(@RequestBody PetCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetReadDTO> update(@PathVariable Long id, @RequestBody PetUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PetReadDTO>> getAllByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllByUser(userId));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<PetDetailDTO> getPetDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPetDetailById(id));
    }
}
