package edu.sena.petcare.dto.VaccinationHistory;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccinationHistoryUpdateDTO {
    private String nombre;
    private String descripcion;
    private String fechaAplicacion;
    private boolean activa;
}
