package edu.sena.petcare.dto.vaccinationhistory;

import lombok.*;

//creacion de nuevas vacunas

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccinationHistoryCreateDTO {
    private String nombre;
    private String descripcion;
    private String fechaAplicacion; 
}
