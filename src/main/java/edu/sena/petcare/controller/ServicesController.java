package edu.sena.petcare.controller;

import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;
import edu.sena.petcare.services.service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<ServiceReadDTO> createService(@Valid @RequestBody ServiceNewUpdateDTO serviceNewUpdateDTO) {
        ServiceReadDTO createdService = servicesService.createService(serviceNewUpdateDTO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceReadDTO>> getAllServices() {
        List<ServiceReadDTO> services = servicesService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceReadDTO> getServiceById(@PathVariable Long id) {
        ServiceReadDTO service = servicesService.getServiceById(id);
        return ResponseEntity.ok(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceReadDTO> updateService(@PathVariable Long id, @Valid @RequestBody ServiceNewUpdateDTO serviceNewUpdateDTO) {
        ServiceReadDTO updatedService = servicesService.updateService(id, serviceNewUpdateDTO);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<ServiceReadDTO>> getServicesByClinicId(@PathVariable Long clinicId) {
        List<ServiceReadDTO> services = servicesService.getServicesByClinicId(clinicId);
        return ResponseEntity.ok(services);
    }
}
