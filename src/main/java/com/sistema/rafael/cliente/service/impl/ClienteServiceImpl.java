package com.sistema.rafael.cliente.service.impl;

import com.sistema.rafael.cliente.domain.Cliente;
import com.sistema.rafael.cliente.dto.*;
import com.sistema.rafael.cliente.repository.ClienteRepository;
import com.sistema.rafael.cliente.service.ClienteService;
import com.sistema.rafael.config.exception.ResourceConflictException;
import com.sistema.rafael.config.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    private ClienteResponse mapToResponse(Cliente c) {
        return new ClienteResponse(
                c.getId(),
                c.getRazonSocial(),
                c.getRuc(),
                c.getDireccion(),
                c.getTelefono(),
                c.getCorreo()
        );
    }

    @Override
    public ClienteResponse crearCliente(ClienteRequest req) {

        if (repo.existsByRuc(req.ruc())) {
            throw new ResourceConflictException("El RUC ya está registrado");
        }

        Cliente cliente = Cliente.builder()
                .razonSocial(req.razonSocial())
                .ruc(req.ruc())
                .direccion(req.direccion())
                .telefono(req.telefono())
                .correo(req.correo())
                .build();

        return mapToResponse(repo.save(cliente));
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return repo.findByActivoTrue()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    public ClienteResponse obtenerCliente(Long id) {
        return repo.findByIdAndActivoTrue(id)
            .map(this::mapToResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    @Override
    public ClienteResponse actualizarCliente(Long id, ClienteRequest req) {
        Cliente c = repo.findByIdAndActivoTrue(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        c.setRazonSocial(req.razonSocial());
        c.setRuc(req.ruc());
        c.setDireccion(req.direccion());
        c.setTelefono(req.telefono());
        c.setCorreo(req.correo());

        return mapToResponse(repo.save(c));
    }

    @Override
    public void eliminarCliente(Long id) {

        Cliente cliente = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setActivo(false);

        repo.save(cliente);
    }
}
