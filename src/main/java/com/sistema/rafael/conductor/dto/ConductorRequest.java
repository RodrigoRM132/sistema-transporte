package com.sistema.rafael.conductor.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConductorRequest {

    @NotBlank
    @Size(min = 8, max = 12)
    private String dni;

    @NotBlank
    @Size(max = 120)
    private String nombres;

    @NotBlank
    @Size(max = 120)
    private String apellidos;

    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 120)
    private String correo;

    @NotBlank
    @Size(max = 20)
    private String licencia;
}
