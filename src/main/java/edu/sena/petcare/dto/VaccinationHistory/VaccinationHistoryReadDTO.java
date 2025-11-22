package edu.sena.petcare.dto.VaccinationHistory;

import lombok.*;

//información de las vacuna.

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccinationHistoryReadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String fechaAplicacion;
    private boolean activa;
}
