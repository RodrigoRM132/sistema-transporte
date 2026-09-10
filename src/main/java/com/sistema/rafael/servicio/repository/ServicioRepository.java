package com.sistema.rafael.servicio.repository;

import com.sistema.rafael.servicio.domain.ServicioTransporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository
        extends JpaRepository<ServicioTransporte, Long> {

    List<ServicioTransporte> findByActivoTrue();

    Optional<ServicioTransporte> findByIdAndActivoTrue(Long id);
}
