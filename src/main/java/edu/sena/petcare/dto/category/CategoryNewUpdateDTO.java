package edu.sena.petcare.dto.category;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNewUpdateDTO {

    private String name;
    private List<Long> subcategorias;
}
