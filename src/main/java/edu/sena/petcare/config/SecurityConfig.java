package edu.sena.petcare.config;

import edu.sena.petcare.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        // AuthenticationProvider removed - Spring Security auto-configures it

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(auth -> auth
                                                // ⚠️ WARNING: DEVELOPMENT CONFIGURATION ONLY ⚠️
                                                // This configuration permits ALL requests for Swagger testing
                                                // REMOVE OR RESTRICT BEFORE DEPLOYING TO PRODUCTION

                                                // Swagger UI - MUST BE FIRST
                                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
                                                                "/swagger-ui.html", "/swagger-resources/**",
                                                                "/webjars/**")
                                                .permitAll()

                                                // Public Auth Endpoints
                                                .requestMatchers("/auth/**").permitAll()

                                                // Public Endpoints (GET)
                                                .requestMatchers(HttpMethod.GET, "/products/**", "/services/**",
                                                                "/veterinaryClinic/**",
                                                                "/subCategory/**", "/Category/**", "/specie/**",
                                                                "/race/**",
                                                                "/neighborhood/**",
                                                                "/locality/**", "/documentType/**")
                                                .permitAll()

                                                // ⚠️ DEVELOPMENT ONLY: Permit all other requests for Swagger testing
                                                // TODO: Remove this line before production deployment
                                                .anyRequest().permitAll())
                                .sessionManagement(
                                                session -> session
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // AuthenticationProvider removed - Spring Security auto-configures it
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
