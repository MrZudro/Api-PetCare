package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.veterinaryclinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.services.veterinaryclinic.VeterinaryClinicService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/veterinary-clinics")
@RequiredArgsConstructor
public class VeterinaryClinicController {

    private final VeterinaryClinicService veterinaryClinicService;

    @GetMapping
    public ResponseEntity<List<VeterinaryClinicReadDTO>> getAll() {
        return ResponseEntity.ok(veterinaryClinicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinaryClinicReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(veterinaryClinicService.getById(id));
    }
}
