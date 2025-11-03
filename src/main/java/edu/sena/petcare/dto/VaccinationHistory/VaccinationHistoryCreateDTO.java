package edu.sena.petcare.dto.VaccinationHistory;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccinationHistoryCreateDTO {
    private String nombre;
    private String descripcion;
    private String fechaAplicacion; 
}
