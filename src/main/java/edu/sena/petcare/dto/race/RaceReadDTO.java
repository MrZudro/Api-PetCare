package edu.sena.petcare.dto.race;

import edu.sena.petcare.models.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceReadDTO {
    private Long id;
    private String name;
    private List<Pet> mascotas;

}
