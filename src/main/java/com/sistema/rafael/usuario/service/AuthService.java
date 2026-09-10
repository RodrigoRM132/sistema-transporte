package com.sistema.rafael.usuario.service;

import com.sistema.rafael.security.JwtService;

import com.sistema.rafael.usuario.Usuario;
import com.sistema.rafael.usuario.UsuarioDetails;
import com.sistema.rafael.usuario.UsuarioRepository;

import com.sistema.rafael.usuario.dto.RegisterRequest;
import com.sistema.rafael.usuario.dto.LoginResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public LoginResponse register(RegisterRequest request) {

        // VALIDAR SI YA EXISTE
        if (usuarioRepository.existsByUsername(
                request.getUsername()
        )) {

            throw new RuntimeException(
                    "El usuario ya existe"
            );
        }

        // CREAR USUARIO
        Usuario usuario = new Usuario();

        usuario.setUsername(request.getUsername());

        usuario.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        usuario.setRole(request.getRole());

        // GUARDAR
        usuarioRepository.save(usuario);

        // GENERAR TOKEN
        String token = jwtService.generateToken(
                new UsuarioDetails(usuario)
        );

        return new LoginResponse(token);
    }
}