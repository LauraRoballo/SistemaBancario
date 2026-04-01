package com.banco.banco_api.repository;

import com.banco.banco_api.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {

    void deleteByNumeroCuenta (String numeroCuenta);

    boolean existsByNumeroCuenta(String numeroCuenta);// Confirma si existe ya el numero de cuenta

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    List<Cuenta> findByClienteDocumento(String documento);
}
