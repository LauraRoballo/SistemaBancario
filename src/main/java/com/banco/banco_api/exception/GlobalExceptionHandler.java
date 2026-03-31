package com.banco.banco_api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura errores de validación (Email, Nombre vacío, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errores);
    }

    // 2. Captura errores de duplicados (Documento o Email repetido)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> manejarDuplicados(DataIntegrityViolationException ex) {
        String mensaje = "Error de integridad: el dato ya existe en el sistema.";

        // Intentamos ser más específicos si el error menciona el campo
        if (ex.getMessage() != null && ex.getMessage().contains("documento")) {
            mensaje = "El número de documento ya se encuentra registrado.";
        } else if (ex.getMessage() != null && ex.getMessage().contains("email")) {
            mensaje = "El correo electrónico ya se encuentra registrado.";
        }

        return ResponseEntity.badRequest().body(Map.of("error", mensaje));
    }
}