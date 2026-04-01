package com.banco.banco_api.repository;

import com.banco.banco_api.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    // Buscar todos los movimientos donde la cuenta participó (como origen o destino)
    List<Transaccion> findByNumeroCuentaOrigenOrNumeroCuentaDestinoOrderByFechaDesc(String origen, String destino);
}