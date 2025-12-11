package edu.sena.petcare.controller;

import java.util.List;

import edu.sena.petcare.dto.customer.CustomerNewUpdateDTO;
import edu.sena.petcare.dto.customer.CustomerReadDTO;
import edu.sena.petcare.services.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerReadDTO> create(@Valid @RequestBody CustomerNewUpdateDTO dto) {
        CustomerReadDTO created = customerService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerReadDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerReadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerReadDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerNewUpdateDTO dto) {
        return ResponseEntity.ok(customerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody java.util.Map<String, String> request) {
        try {
            // Get authenticated user email from SecurityContext
            org.springframework.security.core.Authentication authentication = org.springframework.security.core.context.SecurityContextHolder
                    .getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                        .body("Usuario no autenticado");
            }

            String email = authentication.getName();
            String currentPassword = request.get("currentPassword");
            String newPassword = request.get("newPassword");

            customerService.changePassword(email, currentPassword, newPassword);
            return ResponseEntity.ok("Contraseña actualizada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
