package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.services.methodpaymentcustomer.MethodPaymentCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class MethodPaymentCustomerController {

    private final MethodPaymentCustomerService methodPaymentCustomerService;

    /**
     * Obtiene todos los métodos de pago (admin)
     */
    @GetMapping
    public ResponseEntity<List<MethodPaymentCustomerReadDTO>> getAll() {
        return ResponseEntity.ok(methodPaymentCustomerService.getAll());
    }

    /**
     * Obtiene un método de pago por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MethodPaymentCustomerReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(methodPaymentCustomerService.getById(id));
    }

    /**
     * Obtiene todos los métodos de pago de un usuario específico
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MethodPaymentCustomerReadDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(methodPaymentCustomerService.getByUserId(userId));
    }

    /**
     * Crea un nuevo método de pago para un usuario
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<MethodPaymentCustomerReadDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody MethodPaymentCustomerNewUpdateDTO dto) {
        MethodPaymentCustomerReadDTO created = methodPaymentCustomerService.create(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Elimina un método de pago (validando ownership)
     */
    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long userId) {
        methodPaymentCustomerService.delete(id, userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marca un método de pago como predeterminado
     */
    @PutMapping("/{id}/default/user/{userId}")
    public ResponseEntity<MethodPaymentCustomerReadDTO> setAsDefault(
            @PathVariable Long id,
            @PathVariable Long userId) {
        MethodPaymentCustomerReadDTO updated = methodPaymentCustomerService.setAsDefault(id, userId);
        return ResponseEntity.ok(updated);
    }
}
