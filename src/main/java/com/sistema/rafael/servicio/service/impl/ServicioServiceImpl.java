package com.sistema.rafael.servicio.service.impl;

import com.sistema.rafael.cliente.domain.Cliente;
import com.sistema.rafael.cliente.repository.ClienteRepository;
import com.sistema.rafael.conductor.domain.Conductor;
import com.sistema.rafael.conductor.repository.ConductorRepository;
import com.sistema.rafael.vehiculo.domain.Vehiculo;
import com.sistema.rafael.vehiculo.repository.VehiculoRepository;

import com.sistema.rafael.config.exception.ResourceNotFoundException;

import com.sistema.rafael.servicio.domain.EstadoServicio;
import com.sistema.rafael.servicio.domain.ServicioTransporte;
import com.sistema.rafael.servicio.dto.ServicioRequest;
import com.sistema.rafael.servicio.dto.ServicioResponse;
import com.sistema.rafael.servicio.repository.ServicioRepository;
import com.sistema.rafael.servicio.service.ServicioService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repo;
    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;
    private final ConductorRepository conductorRepo;

    @Override
    public ServicioResponse crear(ServicioRequest r) {

        Cliente cliente = clienteRepo.findByIdAndActivoTrue(r.clienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado o está inactivo")
                );

        Vehiculo vehiculo = vehiculoRepo.findByIdAndActivoTrue(r.vehiculoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehículo no encontrado o está inactivo")
                );

        Conductor conductor = conductorRepo.findByIdAndActivoTrue(r.conductorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conductor no encontrado o está inactivo")
                );

        ServicioTransporte servicio = ServicioTransporte.builder()
                .cliente(cliente)
                .vehiculo(vehiculo)
                .conductor(conductor)
                .fechaServicio(r.fechaServicio())
                .origen(r.origen())
                .destino(r.destino())
                .descripcionCarga(r.descripcionCarga())
                .pesoToneladas(r.pesoToneladas())
                .monto(r.monto())
                .estado(r.estado() != null
                        ? r.estado()
                        : EstadoServicio.PENDIENTE)
                .activo(true)
                .build();

        return toResponse(repo.save(servicio));
    }

    @Override
    public ServicioResponse actualizar(Long id, ServicioRequest r) {

        ServicioTransporte servicio = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Servicio no encontrado")
                );

        Cliente cliente = clienteRepo.findByIdAndActivoTrue(r.clienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado o está inactivo")
                );

        Vehiculo vehiculo = vehiculoRepo.findByIdAndActivoTrue(r.vehiculoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehículo no encontrado o está inactivo")
                );

        Conductor conductor = conductorRepo.findByIdAndActivoTrue(r.conductorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conductor no encontrado o está inactivo")
                );

        servicio.setCliente(cliente);
        servicio.setVehiculo(vehiculo);
        servicio.setConductor(conductor);
        servicio.setFechaServicio(r.fechaServicio());
        servicio.setOrigen(r.origen());
        servicio.setDestino(r.destino());
        servicio.setDescripcionCarga(r.descripcionCarga());
        servicio.setPesoToneladas(r.pesoToneladas());
        servicio.setMonto(r.monto());

        if (r.estado() != null) {
            servicio.setEstado(r.estado());
        }

        return toResponse(repo.save(servicio));
    }

    @Override
    public ServicioResponse obtener(Long id) {

        ServicioTransporte servicio = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Servicio no encontrado")
                );

        return toResponse(servicio);
    }

    @Override
    public List<ServicioResponse> listar() {

        return repo.findByActivoTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        ServicioTransporte servicio = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Servicio no encontrado")
                );

        servicio.setActivo(false);

        repo.save(servicio);
    }

    private ServicioResponse toResponse(ServicioTransporte s) {

        return new ServicioResponse(
                s.getId(),

                s.getCliente().getId(),
                s.getCliente().getRazonSocial(),

                s.getVehiculo().getId(),
                s.getVehiculo().getPlaca(),

                s.getConductor().getId(),
                s.getConductor().getNombres(),

                s.getFechaServicio(),
                s.getOrigen(),
                s.getDestino(),
                s.getDescripcionCarga(),
                s.getPesoToneladas(),
                s.getMonto(),
                s.getEstado()
        );
    }
}