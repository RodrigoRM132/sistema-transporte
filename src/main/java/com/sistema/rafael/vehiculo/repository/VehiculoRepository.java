package com.sistema.rafael.vehiculo.repository;

import com.sistema.rafael.vehiculo.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

    List<Vehiculo> findByActivoTrue();

    Optional<Vehiculo> findByIdAndActivoTrue(Long id);
}