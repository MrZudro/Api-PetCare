package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.locality.LocalityReadDTO;
import edu.sena.petcare.services.locality.LocalityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/localities")
@RequiredArgsConstructor
public class LocalityController {

    private final LocalityService localityService;

    @GetMapping
    public ResponseEntity<List<LocalityReadDTO>> getAll() {
        return ResponseEntity.ok(localityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalityReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(localityService.getById(id));
    }
}
