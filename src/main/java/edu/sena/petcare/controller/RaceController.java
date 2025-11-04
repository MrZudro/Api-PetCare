package edu.sena.petcare.controller;


import edu.sena.petcare.dto.race.RaceNewUpdateDTO;
import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.services.RaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @PostMapping
    public ResponseEntity<RaceReadDTO> create(@Valid @RequestBody RaceNewUpdateDTO dto) {
        return new ResponseEntity<>(raceService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RaceReadDTO>> getAllActive() {
        return ResponseEntity.ok(raceService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceReadDTO> getByIdActive(@PathVariable Long id) {
        return ResponseEntity.ok(raceService.findByIdActive(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceReadDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RaceNewUpdateDTO dto) {
        return ResponseEntity.ok(raceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        raceService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}