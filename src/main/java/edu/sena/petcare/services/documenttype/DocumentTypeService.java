package edu.sena.petcare.services.documenttype;

import java.util.List;

import edu.sena.petcare.dto.documenttype.DocumentTypeReadDTO;

public interface DocumentTypeService {
    List<DocumentTypeReadDTO> getAll();

    DocumentTypeReadDTO getById(Long id);
}
