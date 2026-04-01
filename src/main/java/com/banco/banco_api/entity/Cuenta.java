package com.banco.banco_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table (name ="cuentas")
@Data
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String numeroCuenta;

    @Column(nullable = false)
    @NotBlank(message = "Ingresar tipo de cuenta")
    private String tipoCuenta;

    @NotNull(message = "El saldo no puede ser nulo")
    @Min(value = 0, message = "El saldo no puede ser negativo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnoreProperties("cuentas")
    private Cliente cliente;

    @PrePersist
    public void prepararCuenta (){
        this.numeroCuenta = String.valueOf((long)(Math.random() * 8999999999L) + 1000000000L);
    }
}