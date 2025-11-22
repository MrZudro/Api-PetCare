package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.services.bill.BillService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    public ResponseEntity<List<BillReadDTO>> getAll() {
        return ResponseEntity.ok(billService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getById(id));
    }
}
