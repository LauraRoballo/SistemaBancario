package com.banco.banco_api.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transferencia {
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private BigDecimal monto;
}
