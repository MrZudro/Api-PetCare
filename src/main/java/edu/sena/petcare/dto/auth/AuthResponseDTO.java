package edu.sena.petcare.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String refreshToken;
    private Long id;
    private edu.sena.petcare.models.enums.Rol role;
    private String names;
    private String lastNames;
    private edu.sena.petcare.models.enums.EmployeeCargo cargo;
    private String profilePhotoUrl;
}
