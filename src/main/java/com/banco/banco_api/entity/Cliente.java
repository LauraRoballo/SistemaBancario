package com.banco.banco_api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="clientes")
@Data

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    private String email;
}
