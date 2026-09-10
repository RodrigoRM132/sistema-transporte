package com.sistema.rafael.cliente.dto;

import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotBlank(message = "La razón social es obligatoria")
        String razonSocial,

        @NotBlank @Size(min = 11, max = 11)
        String ruc,

        String direccion,

        @NotBlank(message = "El teléfono es obligatorio")
        String telefono,

        @Email(message = "Correo no válido")
        String correo
) {}
