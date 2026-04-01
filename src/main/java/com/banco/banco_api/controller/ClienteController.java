package com.banco.banco_api.controller;

import com.banco.banco_api.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Cliente guardarCliente(@Valid @RequestBody Cliente nuevoCliente){
        return repository.save(nuevoCliente);
    }

    @GetMapping("/documento/{documento}") //endpoint para buscar por documento
    public ResponseEntity<Cliente> buscarPorDocumento(@PathVariable String documento) {
        return repository.findByDocumento(documento)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
