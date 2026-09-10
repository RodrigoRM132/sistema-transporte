package com.sistema.rafael.cliente.service;

import com.sistema.rafael.cliente.dto.*;

import java.util.List;

public interface ClienteService {

    ClienteResponse crearCliente(ClienteRequest request);

    List<ClienteResponse> listarClientes();

    ClienteResponse obtenerCliente(Long id);

    ClienteResponse actualizarCliente(Long id, ClienteRequest request);

    void eliminarCliente(Long id);
}
