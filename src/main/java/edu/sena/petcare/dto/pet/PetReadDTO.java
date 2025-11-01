package edu.sena.petcare.dto.pet;


import lombok.Data;
import java.time.LocalDate;

@Data
public class PetReadDTO {

    private Long id;
    private String imageUrl;
    private String name;
    private LocalDate birthdate;
    private String microchip;
    private String color;
    private String weight;
    private String gender;

    // IDs de las relaciones
    private Long raceId;
    private Long userId;
}