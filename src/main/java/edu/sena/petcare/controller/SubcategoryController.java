package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.subcategory.SubcategoryReadDTO;
import edu.sena.petcare.services.subcategory.SubcategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryReadDTO>> getAll() {
        return ResponseEntity.ok(subcategoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(subcategoryService.getById(id));
    }
}
