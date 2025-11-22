package edu.sena.petcare.services.subcategory;

import java.util.List;

import edu.sena.petcare.dto.subcategory.SubcategoryReadDTO;

public interface SubcategoryService {
    List<SubcategoryReadDTO> getAll();

    SubcategoryReadDTO getById(Long id);
}
