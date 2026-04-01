package com.banco.banco_api.controller;

import com.banco.banco_api.entity.Cliente;
import com.banco.banco_api.entity.Cuenta;
import com.banco.banco_api.entity.Transaccion;
import com.banco.banco_api.repository.ClienteRepository;
import com.banco.banco_api.repository.CuentaRepository;
import com.banco.banco_api.repository.TransaccionRepository;
import com.banco.banco_api.service.CuentaDTO;
import com.banco.banco_api.service.CuentaService;
import com.banco.banco_api.service.Recarga;
import com.banco.banco_api.service.Transferencia;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository repository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cuenta> listasCuentas() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> guardarCuenta(@RequestBody CuentaDTO dto) {
        // Usamos clienteRepository para buscar por documento
        Cliente cliente = clienteRepository.findByDocumento(dto.getDocumentoCliente())
                .orElseThrow(() -> new RuntimeException("No existe un cliente con ese documento: " + dto.getDocumentoCliente()));

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setTipoCuenta(dto.getTipoCuenta());
        // Validamos que el saldo no sea nulo al crear, se inicia cuenta en 0 si no ingresa saldo
        nuevaCuenta.setSaldo(dto.getSaldo() != null ? dto.getSaldo() : BigDecimal.ZERO);
        nuevaCuenta.setCliente(cliente);

        Cuenta cuentaGuardada = repository.save(nuevaCuenta);
        return new ResponseEntity<>(cuentaGuardada, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarPorCuenta(@PathVariable String numeroCuenta){
        if (repository.existsByNumeroCuenta(numeroCuenta)){
            repository.deleteByNumeroCuenta(numeroCuenta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> realizarTransferencia(@RequestBody Transferencia transferencia) {
        try {
            cuentaService.realizarTransferencia(
                    transferencia.getNumeroCuentaOrigen(),
                    transferencia.getNumeroCuentaDestino(),
                    transferencia.getMonto()
            );
            return new ResponseEntity<>("Transferencia realizada con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recargar")
    public ResponseEntity<String> recargar(@RequestBody Recarga recarga){
        try {
            cuentaService.recargarCuenta(recarga.getNumeroCuenta(), recarga.getMonto());
            return ResponseEntity.ok("Recarga exitosa. Nuevo saldo actualizado.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{documento}")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorDocumento(@PathVariable String documento) {
        List<Cuenta> cuentas = repository.findByClienteDocumento(documento);

        if (cuentas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    // Buscar el historial de movimientos de una cuenta específica
    @GetMapping("/{numeroCuenta}/historial")
    public ResponseEntity<List<Transaccion>> obtenerHistorial(@PathVariable String numeroCuenta) {
        // Buscamos todas las transacciones donde la cuenta fue origen O destino
        List<Transaccion> historial = transaccionRepository
                .findByNumeroCuentaOrigenOrNumeroCuentaDestinoOrderByFechaDesc(numeroCuenta, numeroCuenta);

        if (historial.isEmpty()) {
            // Si no hay movimientos, devolvemos un 204 (No Content)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(historial, HttpStatus.OK);
    }
}

