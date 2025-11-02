package edu.sena.petcare.dto.veterinaryClinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinaryClinicNewUpdateDTO {

    private String address;

    private String phone;

    private String email;

    private String documentNumber;

    private Long documentTypeId;

}
