package com.sistema.rafael.viaje.dto;

import com.sistema.rafael.viaje.domain.EstadoViaje;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViajeResponse(
        Long id,
        Long clienteId,
        Long servicioId,
        Long vehiculoId,
        Long conductorId,
        String origen,
        String destino,
        LocalDate fechaSalida,
        LocalDate fechaLlegada,
        Double pesoToneladas,
        BigDecimal precioAcordado,
        EstadoViaje estado
) {}
