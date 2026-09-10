package com.sistema.rafael.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(

                        "/auth/**",

                        "/v3/api-docs/**",

                        "/swagger-ui/**",

                        "/swagger-ui.html"

                ).permitAll()

                    // rutas públicas
                    .requestMatchers("/auth/**").permitAll()

                    // ADMIN
                    .requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                    // DRIVER
                    .requestMatchers("/api/driver/**")
                    .hasRole("DRIVER")

                    // CLIENT
                    .requestMatchers("/api/client/**")
                    .hasRole("CLIENT")

                    // cualquier otra requiere login
                    .anyRequest()
                    .permitAll()
            )

            .authenticationProvider(authenticationProvider)

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}