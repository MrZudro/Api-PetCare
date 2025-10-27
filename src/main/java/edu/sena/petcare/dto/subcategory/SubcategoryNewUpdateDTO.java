package edu.sena.petcare.dto.subcategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryNewUpdateDTO {
    private String name;
    private Long categoryId;  // ID de la categoría a la que pertenece
}