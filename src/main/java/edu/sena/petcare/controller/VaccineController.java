package edu.sena.petcare.controller;

import edu.sena.petcare.dto.Vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.Vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.Vaccine.VaccineUpdateDTO;
import edu.sena.petcare.services.Vaccine.VaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final VaccineService service;

    public VaccineController(VaccineService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VaccineReadDTO> create(@RequestBody VaccineCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineReadDTO> update(@PathVariable Long id, @RequestBody VaccineUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VaccineReadDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
