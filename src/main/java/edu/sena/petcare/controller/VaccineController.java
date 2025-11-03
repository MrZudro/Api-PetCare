package edu.sena.petcare.controller;

import edu.sena.petcare.dto.vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.vaccine.VaccineUpdateDTO;
import edu.sena.petcare.services.vaccine.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<VaccineReadDTO> create(@RequestBody VaccineCreateDTO dto) {
        return ResponseEntity.ok(vaccineService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<VaccineReadDTO>> findAll() {
        return ResponseEntity.ok(vaccineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineReadDTO> update(@PathVariable Long id, @RequestBody VaccineUpdateDTO dto) {
        return ResponseEntity.ok(vaccineService.update(id, dto));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        vaccineService.deactivate(id);
        return ResponseEntity.ok("Vaccine successfully deactivated");
    }
}
