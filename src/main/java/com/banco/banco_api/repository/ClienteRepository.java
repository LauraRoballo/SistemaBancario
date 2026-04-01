package com.banco.banco_api.repository;

import com.banco.banco_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento(String documento);
}
