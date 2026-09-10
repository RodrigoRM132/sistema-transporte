package com.sistema.rafael.cliente.repository;

import com.sistema.rafael.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByRuc(String ruc);

    List<Cliente> findByActivoTrue();

    Optional<Cliente> findByIdAndActivoTrue(Long id);
}