package edu.sena.petcare.dto.Vaccine;

import lombok.*;

//creacion de nuevas vacunas

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineCreateDTO {
    private String nombre;
    private String descripcion;
    private String fechaAplicacion; 
}
