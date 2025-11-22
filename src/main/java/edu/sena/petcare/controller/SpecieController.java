package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.specie.SpecieReadDTO;
import edu.sena.petcare.services.specie.SpecieService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpecieController {

    private final SpecieService specieService;

    @GetMapping
    public ResponseEntity<List<SpecieReadDTO>> getAll() {
        return ResponseEntity.ok(specieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(specieService.getById(id));
    }
}
