package edu.sena.petcare.dto.subcategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryReadDTO {
    private Long id;
    private String name;
    private Long categoryId;      // ID de la categoría
    private String categoryName;  // Nombre de la categoría
}
