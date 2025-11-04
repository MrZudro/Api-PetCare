package edu.sena.petcare.dto.race;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceNewUpdateDTO {
    @NotEmpty(message = "El nombre de la raza no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
    private String name;

    @NotNull(message = "El ID de la especie es obligatorio.")
    private Long speciesId;
}
