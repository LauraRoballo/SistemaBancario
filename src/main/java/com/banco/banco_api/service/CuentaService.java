package com.banco.banco_api.service;

import com.banco.banco_api.entity.Cuenta;
import com.banco.banco_api.entity.Transaccion;
import com.banco.banco_api.repository.CuentaRepository;
import com.banco.banco_api.repository.TransaccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository repository;

    @Transactional
    public void realizarTransferencia(String cuentaOrig, String cuentaDest, BigDecimal monto) {

        // Buscar las cuentas usando el Optional del Repository
        Cuenta origen = repository.findByNumeroCuenta(cuentaOrig)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        Cuenta destino = repository.findByNumeroCuenta(cuentaDest)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        // 2. Validar que la cuenta origen tenga suficiente dinero

        if (origen.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Saldo insuficiente en la cuenta origen");
        }

        // Realizar la operación matemática (Sustraer y Sumar)
        origen.setSaldo(origen.getSaldo().subtract(monto));
        destino.setSaldo(destino.getSaldo().add(monto));

        Transaccion registro = new Transaccion(cuentaOrig, cuentaDest, monto, "TRANSFERENCIA");
        transaccionRepository.save(registro);

        // Guardar los cambios en ambas cuentas
        repository.save(origen);
        repository.save(destino);


    }

    @Transactional
    public void recargarCuenta(String numeroCuenta, BigDecimal monto) {
        Cuenta cuenta = repository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("La cuenta no existe"));

        // 2. Sumamos al saldo actual
        cuenta.setSaldo(cuenta.getSaldo().add(monto));

        Transaccion registro = new Transaccion(null, numeroCuenta, monto, "RECARGA");
        transaccionRepository.save(registro);


        repository.save(cuenta);
    }
}