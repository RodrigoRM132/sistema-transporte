package com.sistema.rafael.usuario.controller;

import com.sistema.rafael.security.JwtService;

import com.sistema.rafael.usuario.dto.LoginRequest;
import com.sistema.rafael.usuario.dto.LoginResponse;
import com.sistema.rafael.usuario.dto.RegisterRequest;

import com.sistema.rafael.usuario.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails user =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public LoginResponse register(
            @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }
}