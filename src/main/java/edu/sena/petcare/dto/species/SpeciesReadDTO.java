package edu.sena.petcare.dto.species;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesReadDTO {

    private Long id;
    private String name;
    private String status;
}