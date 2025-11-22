package edu.sena.petcare.services;

import edu.sena.petcare.dtos.AuthResponseDTO;
import edu.sena.petcare.dtos.LoginRequestDTO;
import edu.sena.petcare.dtos.RegisterRequestDTO;
import edu.sena.petcare.exceptions.TokenExpired;
import edu.sena.petcare.models.Customer;
import edu.sena.petcare.models.Token;
import edu.sena.petcare.models.User;
import edu.sena.petcare.models.enums.Rol;
import edu.sena.petcare.models.enums.TokenType;
import edu.sena.petcare.repositories.CustomerRepository;
import edu.sena.petcare.repositories.TokenRepository;
import edu.sena.petcare.repositories.UserRepository;
import edu.sena.petcare.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        var user = new Customer();
        user.setNames(request.getNames());
        user.setLastNames(request.getLastNames());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Rol.CUSTOMER);
        user.setBirthDate(request.getBirthDate());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        // Set other fields like documentType and neighborhood if needed, assuming they
        // are fetched or set elsewhere
        // For simplicity, we are skipping relation setting here, but in real app we
        // should fetch them.

        var savedUser = customerRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public String forgotPassword(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String resetToken = java.util.UUID.randomUUID().toString();
        user.setResetKey(resetToken);
        user.setResetDate(java.time.LocalDateTime.now().plusHours(1)); // 1 hour expiration
        userRepository.save(user);
        return resetToken; // In a real app, send this via email
    }

    public void resetPassword(String token, String newPassword) {
        var user = userRepository.findByResetKey(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getResetDate().isBefore(java.time.LocalDateTime.now())) {
            throw new TokenExpired("Token expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetKey(null);
        user.setResetDate(null);
        userRepository.save(user);
    }
}
