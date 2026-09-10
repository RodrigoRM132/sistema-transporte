package com.sistema.rafael.conductor.service.impl;

import com.sistema.rafael.config.exception.ResourceNotFoundException;
import com.sistema.rafael.conductor.domain.Conductor;
import com.sistema.rafael.conductor.dto.ConductorRequest;
import com.sistema.rafael.conductor.dto.ConductorResponse;
import com.sistema.rafael.conductor.repository.ConductorRepository;
import com.sistema.rafael.conductor.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sistema.rafael.config.exception.ResourceConflictException;
import com.sistema.rafael.config.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConductorServiceImpl implements ConductorService {

    private final ConductorRepository repo;

    private ConductorResponse mapToResponse(Conductor conductor) {
        return new ConductorResponse(
                conductor.getId(),
                conductor.getDni(),
                conductor.getNombres(),
                conductor.getApellidos(),
                conductor.getTelefono(),
                conductor.getCorreo(),
                conductor.getLicencia()
        );
    }

    @Override
    public ConductorResponse crear(ConductorRequest request) {

        if (repo.findByDni(request.getDni()).isPresent()) {
            throw new ResourceConflictException("El DNI ya está registrado");
        }

        if (repo.findByLicencia(request.getLicencia()).isPresent()) {
            throw new ResourceConflictException("La licencia ya está registrada");
        }

        Conductor conductor = Conductor.builder()
                .dni(request.getDni())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .licencia(request.getLicencia())
                .build();

        return mapToResponse(repo.save(conductor));
    }

    @Override
    public ConductorResponse actualizar(Long id, ConductorRequest request) {

        Conductor conductor = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conductor no encontrado"));

        conductor.setDni(request.getDni());
        conductor.setNombres(request.getNombres());
        conductor.setApellidos(request.getApellidos());
        conductor.setTelefono(request.getTelefono());
        conductor.setCorreo(request.getCorreo());
        conductor.setLicencia(request.getLicencia());

        return mapToResponse(repo.save(conductor));
    }

    @Override
    @Transactional(readOnly = true)
    public ConductorResponse obtener(Long id) {

        return repo.findByIdAndActivoTrue(id)
                .map(this::mapToResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conductor no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConductorResponse> listar() {

        return repo.findByActivoTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Conductor conductor = repo.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conductor no encontrado"));

        conductor.setActivo(false);

        repo.save(conductor);
    }
}