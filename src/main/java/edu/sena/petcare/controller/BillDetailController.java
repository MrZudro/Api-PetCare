package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.billdetail.BillDetailReadDTO;
import edu.sena.petcare.services.billdetail.BillDetailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bill-details")
@RequiredArgsConstructor
public class BillDetailController {

    private final BillDetailService billDetailService;

    @GetMapping
    public ResponseEntity<List<BillDetailReadDTO>> getAll() {
        return ResponseEntity.ok(billDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDetailReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(billDetailService.getById(id));
    }
}
