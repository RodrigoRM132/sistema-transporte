package com.sistema.rafael.vehiculo.dto;

import com.sistema.rafael.vehiculo.domain.TipoVehiculo;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class VehiculoRequest {

    @NotBlank(message = "La placa es obligatoria")
    private String placa;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Min(value = 1980, message = "Año inválido")
    private int anioFabricacion;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a cero")
    private Double capacidadCarga;

    @NotNull(message = "Debe seleccionar un tipo")
    private TipoVehiculo tipo;
}