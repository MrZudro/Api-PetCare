package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.billtaxes.BillTaxesReadDTO;
import edu.sena.petcare.services.billtaxes.BillTaxesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bill-taxes")
@RequiredArgsConstructor
public class BillTaxesController {

    private final BillTaxesService billTaxesService;

    @GetMapping
    public ResponseEntity<List<BillTaxesReadDTO>> getAll() {
        return ResponseEntity.ok(billTaxesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillTaxesReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(billTaxesService.getById(id));
    }
}
