package edu.sena.petcare.dto.customer;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReadDTO {
    private Long id;
    private String names;
    private String lastNames;
    private String documentNumber;
    private String email;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private Long documentTypeId;
    private Long neighborhoodId;
}


