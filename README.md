# Sistema bancario 🏦

## Descripción del proyecto 

Sistema bancario desarrollado como API REST encargado del registro de clientes y creación de cuentas de ahorro, permitiendo realizar transferencias, recargas de cuenta y consulta de movimientos. La aplicación implementa lógica de negocio bancaria, arquitectura en capas y conexión a base de datos relacional. El sistema permite registrar clientes, crear cuentas, realizar operaciones y consultar movimientos mediante servicios REST.

## Tecnologias 
* Java

* Spring Boot

* Base de datos SQL relacional (MySQL)

* Postman (pruebas de endpoints)

* JSON (intercambio de datos)

## Arquitectura

El sistema implementa arquitectura en capas separando Controller, Service y Repository, permitiendo mantener una organización clara del código y separación de responsabilidades.

## Entidades

El sistema maneja las entidades Cliente, Cuenta y Movimiento, relacionadas entre sí mediante claves foráneas.

## Endpoints

La API expone endpoints REST para la gestión de clientes, cuentas y operaciones bancarias.

## Diagramas

 [Diagramas](./docs/diseno.md)
