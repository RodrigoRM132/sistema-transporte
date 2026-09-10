package com.sistema.rafael.viaje.dto;

import com.sistema.rafael.viaje.domain.EstadoViaje;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViajeRequest(

        @NotNull(message = "El cliente es obligatorio")
        Long clienteId,

        @NotNull(message = "El servicio es obligatorio")
        Long servicioId,

        @NotNull(message = "El vehículo es obligatorio")
        Long vehiculoId,

        @NotNull(message = "El conductor es obligatorio")
        Long conductorId,

        @NotBlank(message = "El origen es obligatorio")
        String origen,

        @NotBlank(message = "El destino es obligatorio")
        String destino,

        @NotNull(message = "La fecha de salida es obligatoria")
        @FutureOrPresent(message = "La fecha de salida no puede ser anterior a hoy")
        LocalDate fechaSalida,

        @NotNull(message = "La fecha de llegada es obligatoria")
        @FutureOrPresent(message = "La fecha de llegada no puede ser anterior a hoy")
        LocalDate fechaLlegada,

        @NotNull(message = "El peso es obligatorio")
        @Positive(message = "El peso debe ser mayor que cero")
        Double pesoToneladas,

        @NotNull(message = "El precio acordado es obligatorio")
        @Positive(message = "El precio debe ser mayor que cero")
        BigDecimal precioAcordado,

        EstadoViaje estado

) {}