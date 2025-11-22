package edu.sena.petcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sena.petcare.dto.documenttype.DocumentTypeReadDTO;
import edu.sena.petcare.services.documenttype.DocumentTypeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/document-types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentTypeReadDTO>> getAll() {
        return ResponseEntity.ok(documentTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(documentTypeService.getById(id));
    }
}
