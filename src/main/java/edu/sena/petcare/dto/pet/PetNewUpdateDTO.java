package edu.sena.petcare.dto.pet;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetNewUpdateDTO {

    @Size(max = 300, message = "La URL de la imagen no puede exceder los 300 caracteres.")
    private String imageUrl;

    @NotEmpty(message = "El nombre de la mascota no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.")
    private String name;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate birthDate;

    @Size(max = 20, message = "El microchip no puede exceder los 20 caracteres.")
    private String microchip;

    @Size(max = 15, message = "El color no puede exceder los 15 caracteres.")
    private String color;

    @Size(max = 10, message = "El peso no puede exceder los 10 caracteres.")
    private String weight;

    @NotEmpty(message = "El género es obligatorio.")
    @Size(max = 10, message = "El género no puede exceder los 10 caracteres.")
    private String gender;

    @NotNull(message = "El ID de la raza es obligatorio.")
    private Long raceId;

    @NotNull(message = "El ID del usuario (dueño) es obligatorio.")
    private Long userId;
}