package com.banco.banco_api.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Recarga {
    private String numeroCuenta;
    private BigDecimal monto;
}
