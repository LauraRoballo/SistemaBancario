package com.banco.banco_api.controller;

import com.banco.banco_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.banco.banco_api.entity.Cliente;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> listarClientes(){
        return repository.findAll();
    }

    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente nuevoCliente){
        return repository.save(nuevoCliente);
    }
}
