package com.sistema.rafael.servicio.service;

import com.sistema.rafael.servicio.dto.ServicioRequest;
import com.sistema.rafael.servicio.dto.ServicioResponse;

import java.util.List;

public interface ServicioService {
    ServicioResponse crear(ServicioRequest request);
    ServicioResponse actualizar(Long id, ServicioRequest request);
    ServicioResponse obtener(Long id);
    List<ServicioResponse> listar();
    void eliminar(Long id);
}
