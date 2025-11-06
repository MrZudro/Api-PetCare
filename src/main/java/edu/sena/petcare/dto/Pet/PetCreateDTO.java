package edu.sena.petcare.dto.Pet;

import lombok.*;

//creacion nueva mascota

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetCreateDTO {

    private String imageUrl;
    private String name;
    private String birthdate; 
    private String microchip;
    private String color;
    private String weight;
    private String gender;
    private Long idRace;
    private Long idUser;
}
