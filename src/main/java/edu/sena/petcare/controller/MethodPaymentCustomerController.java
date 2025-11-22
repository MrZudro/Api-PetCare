package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.services.methodpaymentcustomer.MethodPaymentCustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class MethodPaymentCustomerController {

    private final MethodPaymentCustomerService methodPaymentCustomerService;

    @GetMapping
    public ResponseEntity<List<MethodPaymentCustomerReadDTO>> getAll() {
        return ResponseEntity.ok(methodPaymentCustomerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MethodPaymentCustomerReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(methodPaymentCustomerService.getById(id));
    }
}
