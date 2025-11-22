package edu.sena.petcare.config;

import edu.sena.petcare.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        // Constantes
        private static final String URL_APPOINTMENT = "/Appointment/**";
        private static final String URL_CUSTOMER = "/Customer/**";
        private static final String URL_PET = "/Pet/**";
        private static final String AUTHORITY_CASHIER = "CARGO_CASHIER";

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(auth -> auth
                                                // Swagger UI - MUST BE FIRST
                                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
                                                                "/swagger-ui.html")
                                                .permitAll()

                                                // Public Auth Endpoints - MUST BE BEFORE OTHER RULES
                                                .requestMatchers("/auth/**").permitAll()

                                                // Public Endpoints (GET)
                                                .requestMatchers(HttpMethod.GET, "/products/**", "/services/**",
                                                                "/veterinaryClinic/**",
                                                                "/subCategory/**", "/Category/**", "/specie/**",
                                                                "/race/**", "/neighborhood/**",
                                                                "/locality/**", "/documentType/**")
                                                .permitAll()

                                                // Customer Role
                                                .requestMatchers("/Consultation/**", "/petConditions/**",
                                                                "/schedule/**",
                                                                "/vaccinationHistory/**", "/wishlist/**")
                                                .hasAuthority("CUSTOMER")
                                                .requestMatchers(URL_APPOINTMENT, URL_CUSTOMER,
                                                                "/MethodPaymentCustomer/**", URL_PET,
                                                                "/User/**", "/Wishlist/**")
                                                .hasAuthority("CUSTOMER")

                                                // Admin Role
                                                // GET only
                                                .requestMatchers(HttpMethod.GET, "/Bill/**", URL_CUSTOMER, URL_PET,
                                                                "/Transactions/**",
                                                                "/User/**")
                                                .hasAuthority("ADMIN")
                                                // Full Access
                                                .requestMatchers(URL_APPOINTMENT, "/Category/**", "/Conditions/**",
                                                                "/Employee/**",
                                                                "/VeterinaryClinic/**", "/Product/**", "/Schedule/**",
                                                                "/Subcategory/**",
                                                                "/Services/**")
                                                .hasAuthority("ADMIN")

                                                // Employee (VETERINARIAN)
                                                // GET only
                                                .requestMatchers(HttpMethod.GET, URL_APPOINTMENT, "/Consultation/**",
                                                                URL_CUSTOMER,
                                                                URL_PET, "/PetConditions/**", "/Schedule/**")
                                                .hasAuthority("CARGO_VETERINARIAN")
                                                // Full Access
                                                .requestMatchers("/VaccinationHistory/**")
                                                .hasAuthority("CARGO_VETERINARIAN")

                                                // Employee (CASHIER)
                                                // GET only
                                                .requestMatchers(HttpMethod.GET, URL_APPOINTMENT, "/transactions/**",
                                                                "/bill/**")
                                                .hasAuthority(AUTHORITY_CASHIER)
                                                // POST and GET
                                                .requestMatchers(HttpMethod.GET, URL_PET, URL_CUSTOMER)
                                                .hasAuthority(AUTHORITY_CASHIER)
                                                .requestMatchers(HttpMethod.POST, URL_PET, URL_CUSTOMER)
                                                .hasAuthority(AUTHORITY_CASHIER)

                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(List.of("*")); // Allow all origins for dev
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With",
                                "Accept",
                                "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
                configuration
                                .setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin",
                                                "Access-Control-Allow-Credentials"));
                configuration.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
