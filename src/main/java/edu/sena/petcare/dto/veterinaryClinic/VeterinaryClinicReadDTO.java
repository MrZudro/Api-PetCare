package edu.sena.petcare.dto.veterinaryClinic;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinaryClinicReadDTO {

    private Long id;

    private String address;

    private String phone;

    private String email;

    private String documentNumber;

    private Long documentTypeId;

    private List<Long> serviceIds;

    private List<Long> billIds;
}