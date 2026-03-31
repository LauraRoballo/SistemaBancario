package com.banco.banco_api.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="clientes")
@Data

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Debe diligenciar este campo")
    private String nombre;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Debe diligenciar este campo")
    private String documento;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Debe diligenciar este campo")
    @Email(message = "Escribir un correo valido")
    private String email;


}
