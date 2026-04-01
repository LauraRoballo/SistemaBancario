package com.banco.banco_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private BigDecimal monto;
    private String tipo; // "TRANSFERENCIA" o "RECARGA"
    private LocalDateTime fecha;

    public Transaccion(String origen, String destino, BigDecimal monto, String tipo) {
        this.numeroCuentaOrigen = origen;
        this.numeroCuentaDestino = destino;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    public Transaccion() {}
}