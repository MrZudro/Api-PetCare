package edu.sena.petcare.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String names;
    private String lastNames;
    private String documentNumber;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private Long documentTypeId;
    private Long neighborhoodId;
}
