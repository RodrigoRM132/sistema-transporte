package com.sistema.rafael.conductor.repository;

import com.sistema.rafael.conductor.domain.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    Optional<Conductor> findByDni(String dni);

    Optional<Conductor> findByLicencia(String licencia);

    List<Conductor> findByActivoTrue();

    Optional<Conductor> findByIdAndActivoTrue(Long id);

}