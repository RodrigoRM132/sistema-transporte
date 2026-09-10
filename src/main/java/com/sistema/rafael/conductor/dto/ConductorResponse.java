package com.sistema.rafael.conductor.dto;

public record ConductorResponse(

        Long id,
        String dni,
        String nombres,
        String apellidos,
        String telefono,
        String correo,
        String licencia

) {}