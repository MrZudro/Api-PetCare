package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.services.race.RaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/races")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<List<RaceReadDTO>> getAll() {
        return ResponseEntity.ok(raceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(raceService.getById(id));
    }
}
