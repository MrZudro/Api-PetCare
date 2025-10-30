package edu.sena.petcare.dto.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNewUpdateDTO {

    @NotBlank
    private String names;

    @NotBlank
    private String lastNames;

    @NotBlank
    private String documentNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotNull
    private Long documentTypeId;

    private Long neighborhoodId;

    @NotBlank
    private String employeeCode;

    @NotNull
    private BigDecimal salary;

    @NotBlank
    private String cargo;
}


