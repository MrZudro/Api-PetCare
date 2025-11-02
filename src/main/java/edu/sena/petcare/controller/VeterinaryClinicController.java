package edu.sena.petcare.controller;

import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicNewUpdateDTO;
import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.services.veterinaryClinic.VeterinaryClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinary-clinics")
@RequiredArgsConstructor
public class VeterinaryClinicController {

    private final VeterinaryClinicService veterinaryClinicService;

    // POST /veterinary-clinics
    @PostMapping
    public ResponseEntity<VeterinaryClinicReadDTO> createVeterinaryClinic(
            @Valid @RequestBody VeterinaryClinicNewUpdateDTO newVeterinaryClinicDTO) {
        
        VeterinaryClinicReadDTO createdClinic = veterinaryClinicService.newVeterinaryClinic(newVeterinaryClinicDTO);
        // Retorna 201 Created 
        return new ResponseEntity<>(createdClinic, HttpStatus.CREATED);
    }

    // GET /veterinary-clinics
    @GetMapping
    public ResponseEntity<List<VeterinaryClinicReadDTO>> getAllVeterinaryClinics() {
        List<VeterinaryClinicReadDTO> clinics = veterinaryClinicService.allVeterinaryClinics();
        // Retorna 200 OK 
        return ResponseEntity.ok(clinics);
    }

    // GET /veterinary-clinics/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VeterinaryClinicReadDTO> getVeterinaryClinicById(@PathVariable Long id) {
        VeterinaryClinicReadDTO clinic = veterinaryClinicService.oneSpecificVeterinaryClinic(id);
        // Retorna 200 OK 
        return ResponseEntity.ok(clinic);
    }

    // PUT /veterinary-clinics/{id}
    @PutMapping("/{id}")
    public ResponseEntity<VeterinaryClinicNewUpdateDTO> updateVeterinaryClinic(
            @PathVariable Long id, 
            @Valid @RequestBody VeterinaryClinicNewUpdateDTO updatedVeterinaryClinicDTO) {
            
        VeterinaryClinicNewUpdateDTO updatedClinic = 
            veterinaryClinicService.updateVeterinaryClinic(id, updatedVeterinaryClinicDTO);
        // Retorna 200 OK 
        return ResponseEntity.ok(updatedClinic);
    }

    // DELETE /veterinary-clinics/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinaryClinic(@PathVariable Long id) {
        veterinaryClinicService.deleteVeterinaryClinic(id);
        // Retorna 204 No Content 
        return ResponseEntity.noContent().build();
    }
}