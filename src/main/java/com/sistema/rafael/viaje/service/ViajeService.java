package com.sistema.rafael.viaje.service;

import com.sistema.rafael.viaje.dto.ViajeRequest;
import com.sistema.rafael.viaje.dto.ViajeResponse;

import java.util.List;

public interface ViajeService {
    ViajeResponse crear(ViajeRequest request);
    ViajeResponse actualizar(Long id, ViajeRequest request);
    ViajeResponse obtener(Long id);
    List<ViajeResponse> listar();
    void eliminar(Long id);
}
