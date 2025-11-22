package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.transactions.TransactionsReadDTO;
import edu.sena.petcare.services.transactions.TransactionsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping
    public ResponseEntity<List<TransactionsReadDTO>> getAll() {
        return ResponseEntity.ok(transactionsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.getById(id));
    }
}
