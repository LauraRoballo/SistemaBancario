package com.banco.banco_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cliente") // <--- Aquí le decimos: "Cuando muestres las cuentas, no vuelvas a mostrar al cliente"
    private List<Cuenta> cuentas;
}