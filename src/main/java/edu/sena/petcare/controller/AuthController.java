package edu.sena.petcare.controller;

import edu.sena.petcare.dto.auth.AuthResponseDTO;
import edu.sena.petcare.dto.auth.LoginRequestDTO;
import edu.sena.petcare.dto.auth.RegisterRequestDTO;
import edu.sena.petcare.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "") // Override global security requirement for public auth endpoints
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody edu.sena.petcare.dto.auth.ForgotPasswordRequestDTO request) {
        return ResponseEntity.ok(authService.forgotPassword(request.getEmail()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody edu.sena.petcare.dto.auth.ResetPasswordRequestDTO request) {
        authService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Contraseña restablecida exitosamente");
    }

    @PostMapping("/refresh")
    public ResponseEntity<java.util.Map<String, String>> refreshToken(
            @RequestBody edu.sena.petcare.dto.auth.RefreshTokenRequestDTO request) {
        String newAccessToken = authService.refreshAccessToken(request.getRefreshToken());

        if (newAccessToken != null) {
            java.util.Map<String, String> response = new java.util.HashMap<>();
            response.put("token", newAccessToken);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).build();
    }
}
