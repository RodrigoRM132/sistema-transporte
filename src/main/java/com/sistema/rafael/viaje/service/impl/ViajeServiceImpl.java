package com.sistema.rafael.viaje.service.impl;

import com.sistema.rafael.config.exception.ResourceNotFoundException;
import com.sistema.rafael.cliente.repository.ClienteRepository;
import com.sistema.rafael.conductor.repository.ConductorRepository;
import com.sistema.rafael.servicio.repository.ServicioRepository;
import com.sistema.rafael.vehiculo.repository.VehiculoRepository;
import com.sistema.rafael.viaje.domain.EstadoViaje;
import com.sistema.rafael.viaje.domain.Viaje;
import com.sistema.rafael.viaje.dto.ViajeRequest;
import com.sistema.rafael.viaje.dto.ViajeResponse;
import com.sistema.rafael.viaje.repository.ViajeRepository;
import com.sistema.rafael.viaje.service.ViajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository repo;
    private final ClienteRepository clienteRepo;
    private final ServicioRepository servicioRepo;
    private final VehiculoRepository vehiculoRepo;
    private final ConductorRepository conductorRepo;

    @Override
    public ViajeResponse crear(ViajeRequest r) {
        var cliente = clienteRepo.findById(r.clienteId())
        .orElseThrow(() -> new ResourceNotFoundException(
                "No existe un cliente con id " + r.clienteId()));

        var servicioTransporte = servicioRepo.findById(r.servicioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un servicio con id " + r.servicioId()));

        var vehiculo = vehiculoRepo.findById(r.vehiculoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un vehículo con id " + r.vehiculoId()));

        var conductor = conductorRepo.findById(r.conductorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un conductor con id " + r.conductorId()));

        Viaje v = Viaje.builder()
                .cliente(cliente)
                .servicioTransporte(servicioTransporte)
                .vehiculo(vehiculo)
                .conductor(conductor)
                .origen(r.origen())
                .destino(r.destino())
                .fechaSalida(r.fechaSalida())
                .fechaLlegada(r.fechaLlegada())
                .pesoToneladas(r.pesoToneladas())
                .precioAcordado(r.precioAcordado())
                .estado(r.estado() != null ? r.estado() : EstadoViaje.PROGRAMADO)
                .activo(true)
                .build();

        return toResponse(repo.save(v));
    }

    @Override
    public ViajeResponse actualizar(Long id, ViajeRequest r) {
        Viaje v = repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
                "No existe un viaje con id " + id));

        v.setCliente(clienteRepo.findById(r.clienteId())
        .orElseThrow(() -> new ResourceNotFoundException(
                "No existe un cliente con id " + r.clienteId())));
        v.setServicioTransporte(servicioRepo.findById(r.servicioId()).orElseThrow());
        v.setVehiculo(vehiculoRepo.findById(r.vehiculoId()).orElseThrow());
        v.setConductor(conductorRepo.findById(r.conductorId()).orElseThrow());

        v.setOrigen(r.origen());
        v.setDestino(r.destino());
        v.setFechaSalida(r.fechaSalida());
        v.setFechaLlegada(r.fechaLlegada());
        v.setPesoToneladas(r.pesoToneladas());
        v.setPrecioAcordado(r.precioAcordado());
        v.setEstado(r.estado() != null ? r.estado() : v.getEstado());

        return toResponse(repo.save(v));
    }

    @Override
    public ViajeResponse obtener(Long id) {
        return toResponse(
        repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un viaje con id " + id))
);
    }

    @Override
    public List<ViajeResponse> listar() {
        return repo.findByActivoTrue().stream().map(this::toResponse).toList();
    }

    @Override
    public void eliminar(Long id) {
        Viaje v = repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
                "No existe un viaje con id " + id));
        v.setActivo(false);
        repo.save(v);
    }

    private ViajeResponse toResponse(Viaje v) {
        return new ViajeResponse(
                v.getId(),
                v.getCliente().getId(),
                v.getServicioTransporte().getId(),
                v.getVehiculo().getId(),
                v.getConductor().getId(),
                v.getOrigen(),
                v.getDestino(),
                v.getFechaSalida(),
                v.getFechaLlegada(),
                v.getPesoToneladas(),
                v.getPrecioAcordado(),
                v.getEstado()
        );
    }
}
