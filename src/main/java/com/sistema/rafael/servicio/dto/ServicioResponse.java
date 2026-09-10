package com.sistema.rafael.servicio.dto;

import com.sistema.rafael.servicio.domain.EstadoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ServicioResponse(
        Long id,
        Long clienteId,
        String clienteRazonSocial,

        Long vehiculoId,
        String vehiculoPlaca,

        Long conductorId,
        String conductorNombre,

        LocalDate fechaServicio,
        String origen,
        String destino,
        String descripcionCarga,
        BigDecimal pesoToneladas,
        BigDecimal monto,
        EstadoServicio estado
) {}
