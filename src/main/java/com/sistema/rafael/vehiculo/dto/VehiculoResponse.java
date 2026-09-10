package com.sistema.rafael.vehiculo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistema.rafael.vehiculo.domain.TipoVehiculo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({
    "id",
    "placa",
    "marca",
    "modelo",
    "anioFabricacion",
    "capacidadCarga",
    "tipo",
    "activo"
})
public class VehiculoResponse {

    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private Double capacidadCarga;
    private TipoVehiculo tipo;
    private boolean activo;
}