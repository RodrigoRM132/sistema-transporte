package com.sistema.rafael.cliente.dto;

public record ClienteResponse(
        Long id,
        String razonSocial,
        String ruc,
        String direccion,
        String telefono,
        String correo
) {}
