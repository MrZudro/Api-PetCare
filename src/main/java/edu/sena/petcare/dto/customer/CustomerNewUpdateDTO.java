package edu.sena.petcare.dto.customer;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNewUpdateDTO {

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
}


