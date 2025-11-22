package edu.sena.petcare.dto.race;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceNewUpdateDTO {
    private String name;
    private Long specieId;
}
