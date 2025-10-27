package edu.sena.petcare.dto.category;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReadDTO {
    private Long id;
    private String name;
    private List<Long> subcategoriaIds;

}
