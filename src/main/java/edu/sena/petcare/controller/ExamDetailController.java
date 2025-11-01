package edu.sena.petcare.controller;


import edu.sena.petcare.dto.examdetail.ExamDetailNewUpdateDTO;
import edu.sena.petcare.dto.examdetail.ExamDetailReadDTO;
import edu.sena.petcare.services.examdetail.ExamDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam-details") // Endpoint recomendado
@RequiredArgsConstructor
public class ExamDetailController {

    private final ExamDetailService examDetailService;

    @PostMapping
    public ResponseEntity<ExamDetailReadDTO> create(@Valid @RequestBody ExamDetailNewUpdateDTO dto) {
        return new ResponseEntity<>(examDetailService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExamDetailReadDTO>> getAll() {
        return ResponseEntity.ok(examDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDetailReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(examDetailService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDetailReadDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ExamDetailNewUpdateDTO dto) {
        return ResponseEntity.ok(examDetailService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        examDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}