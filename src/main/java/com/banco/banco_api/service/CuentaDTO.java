package com.banco.banco_api.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaDTO {

        private String tipoCuenta;
        private BigDecimal saldo;
        private String documentoCliente;
    }

