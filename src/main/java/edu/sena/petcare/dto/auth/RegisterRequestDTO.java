package edu.sena.petcare.dto.auth;

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

    private String phone;

    private String profilePhotoUrl;

    @io.swagger.v3.oas.annotations.media.Schema(example = "1")
    private Long documentTypeId;

    @io.swagger.v3.oas.annotations.media.Schema(example = "1")
    private Long neighborhoodId;

    // New fields for Role and Employee
    private edu.sena.petcare.models.enums.Rol role;
    private java.math.BigDecimal salary;
    private edu.sena.petcare.models.enums.EmployeeCargo cargo;
    private String employeeCode;
}
