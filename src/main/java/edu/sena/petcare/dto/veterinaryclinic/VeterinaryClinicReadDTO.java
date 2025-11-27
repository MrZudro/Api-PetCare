package edu.sena.petcare.dto.veterinaryclinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeterinaryClinicReadDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String documentNumber;
    private Long documentTypeId;
    private String documentTypeName;
    private Double puntuacion;
    private String ubicacion;
    private String horarioPrincipal;
    private Integer totalReviews;
}
