package edu.sena.petcare.services.category;

import java.util.List;

import edu.sena.petcare.dto.category.CategoryReadDTO;

public interface CategoryService {
    List<CategoryReadDTO> getAll();

    CategoryReadDTO getById(Long id);
}
