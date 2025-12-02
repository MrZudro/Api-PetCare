package edu.sena.petcare.dto.vaccinationhistory;

import lombok.*;

//actualizacion vacuna

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
