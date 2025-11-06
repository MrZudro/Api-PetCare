package edu.sena.petcare.dto.Pet;

import lombok.*;

//actualizacion de mascotas

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetUpdateDTO {

    private String imageUrl;
    private String color;
    private String weight;
}
