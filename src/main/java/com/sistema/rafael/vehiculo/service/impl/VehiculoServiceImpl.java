package com.sistema.rafael.vehiculo.service.impl;

import com.sistema.rafael.config.exception.ResourceConflictException;
import com.sistema.rafael.config.exception.ResourceNotFoundException;
import com.sistema.rafael.vehiculo.domain.Vehiculo;
import com.sistema.rafael.vehiculo.dto.VehiculoRequest;
import com.sistema.rafael.vehiculo.dto.VehiculoResponse;
import com.sistema.rafael.vehiculo.repository.VehiculoRepository;
import com.sistema.rafael.vehiculo.service.VehiculoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;

    // =========================
    // CREAR VEHÍCULO
    // =========================
    @Override
    public VehiculoResponse crear(VehiculoRequest request) {

        // Verificar que la placa no exista
        if (repository.existsByPlaca(request.getPlaca())) {
                throw new ResourceConflictException(
                        "La placa ya está registrada"
                );
        }

        // Crear entidad
        Vehiculo vehiculo = Vehiculo.builder()
                .placa(request.getPlaca())
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anioFabricacion(request.getAnioFabricacion())
                .capacidadCarga(request.getCapacidadCarga())
                .tipo(request.getTipo())
                .activo(true)
                .build();

        // Guardar y convertir a Response
        return convertir(repository.save(vehiculo));
    }


    // =========================
    // ACTUALIZAR VEHÍCULO
    // =========================
    @Override
    public VehiculoResponse actualizar(
            Long id,
            VehiculoRequest request
    ) {

        // Buscar solamente vehículos activos
        Vehiculo vehiculo = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehículo no encontrado"
                        )
                );

        // Verificar si la nueva placa pertenece a otro vehículo
        if (!vehiculo.getPlaca().equals(request.getPlaca())
        && repository.existsByPlaca(request.getPlaca())) {

                throw new ResourceConflictException(
                        "La placa ya está registrada"
                );
        }

        // Actualizar datos
        vehiculo.setPlaca(request.getPlaca());
        vehiculo.setMarca(request.getMarca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setAnioFabricacion(request.getAnioFabricacion());
        vehiculo.setCapacidadCarga(request.getCapacidadCarga());
        vehiculo.setTipo(request.getTipo());

        return convertir(repository.save(vehiculo));
    }


    // =========================
    // OBTENER VEHÍCULO
    // =========================
    @Override
    public VehiculoResponse obtener(Long id) {

        Vehiculo vehiculo = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehículo no encontrado"
                        )
                );

        return convertir(vehiculo);
    }


    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    @Override
    public List<VehiculoResponse> listar() {

        return repository.findByActivoTrue()
                .stream()
                .map(this::convertir)
                .toList();
    }


    // =========================
    // ELIMINAR VEHÍCULO
    // =========================
    @Override
    public void eliminar(Long id) {

        // Buscar solamente vehículos activos
        Vehiculo vehiculo = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehículo no encontrado"
                        )
                );

        // Baja lógica
        vehiculo.setActivo(false);

        repository.save(vehiculo);
    }


    // =========================
    // CONVERTIR ENTITY → RESPONSE
    // =========================
    private VehiculoResponse convertir(Vehiculo v) {

        return VehiculoResponse.builder()
                .id(v.getId())
                .placa(v.getPlaca())
                .marca(v.getMarca())
                .modelo(v.getModelo())
                .anioFabricacion(v.getAnioFabricacion())
                .capacidadCarga(v.getCapacidadCarga())
                .tipo(v.getTipo())
                .activo(v.getActivo())
                .build();
    }
}