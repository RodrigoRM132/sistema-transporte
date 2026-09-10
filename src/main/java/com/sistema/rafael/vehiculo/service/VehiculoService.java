package com.sistema.rafael.vehiculo.service;

import com.sistema.rafael.vehiculo.dto.VehiculoRequest;
import com.sistema.rafael.vehiculo.dto.VehiculoResponse;

import java.util.List;

public interface VehiculoService {

    VehiculoResponse crear(VehiculoRequest request);

    VehiculoResponse actualizar(Long id, VehiculoRequest request);

    VehiculoResponse obtener(Long id);

    List<VehiculoResponse> listar();

    void eliminar(Long id); // baja lógica
}
