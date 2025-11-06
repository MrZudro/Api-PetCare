package edu.sena.petcare.dto.Pet;

import lombok.*;

//información completa de la mascota

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetReadDTO {

    private Long id;
    private String imageUrl;
    private String name;
    private String birthdate;
    private String microchip;
    private String color;
    private String weight;
    private String gender;
    private String raceName;
    private String userName;
}
