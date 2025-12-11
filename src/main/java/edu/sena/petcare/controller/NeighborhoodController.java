package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.neighborhood.NeighborhoodReadDTO;
import edu.sena.petcare.services.neighborhood.NeighborhoodService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/neighborhoods")
@RequiredArgsConstructor
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    @GetMapping
    public ResponseEntity<List<NeighborhoodReadDTO>> getAll() {
        return ResponseEntity.ok(neighborhoodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeighborhoodReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(neighborhoodService.getById(id));
    }

    @GetMapping("/by-locality/{localityId}")
    public ResponseEntity<List<NeighborhoodReadDTO>> getByLocalityId(@PathVariable Long localityId) {
        return ResponseEntity.ok(neighborhoodService.getByLocalityIdSorted(localityId));
    }
}
