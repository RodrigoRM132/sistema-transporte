package com.sistema.rafael.servicio.dto;

import com.sistema.rafael.servicio.domain.EstadoServicio;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ServicioRequest(
        @NotNull Long clienteId,
        @NotNull Long vehiculoId,
        @NotNull Long conductorId,

        @NotNull LocalDate fechaServicio,

        @NotBlank @Size(max = 120) String origen,
        @NotBlank @Size(max = 120) String destino,

        @Size(max = 250) String descripcionCarga,

        @DecimalMin(value = "0.00") BigDecimal pesoToneladas,

        @NotNull @DecimalMin(value = "0.01") BigDecimal monto,

        EstadoServicio estado
) {}
