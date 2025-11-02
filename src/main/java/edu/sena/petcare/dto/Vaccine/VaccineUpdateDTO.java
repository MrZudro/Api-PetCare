package edu.sena.petcare.dto.Vaccine;

import lombok.*;

//actualizacion vacuna

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineUpdateDTO {
    private String nombre;
    private String descripcion;
    private String fechaAplicacion;
    private boolean activa;
}
