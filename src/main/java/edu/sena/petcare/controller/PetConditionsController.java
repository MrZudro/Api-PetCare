package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.petconditions.PetConditionsReadDTO;
import edu.sena.petcare.services.petconditions.PetConditionsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pet-conditions")
@RequiredArgsConstructor
public class PetConditionsController {

    private final PetConditionsService petConditionsService;

    @GetMapping
    public ResponseEntity<List<PetConditionsReadDTO>> getAll() {
        return ResponseEntity.ok(petConditionsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetConditionsReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(petConditionsService.getById(id));
    }
}
