package com.sistema.rafael.conductor.service;

import com.sistema.rafael.conductor.dto.ConductorRequest;
import com.sistema.rafael.conductor.dto.ConductorResponse;

import java.util.List;

public interface ConductorService {

    ConductorResponse crear(ConductorRequest request);

    ConductorResponse actualizar(Long id, ConductorRequest request);

    ConductorResponse obtener(Long id);

    List<ConductorResponse> listar();

    void eliminar(Long id);

}