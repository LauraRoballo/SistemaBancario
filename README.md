# 🏦 Sistema bancario 

Sistema de gestión bancaria desarrollado como una **API REST** robusta. Permite la administración de clientes, apertura de cuentas de ahorro y la ejecución segura de transacciones financieras con trazabilidad completa de movimientos.

---

## 🚀 Características principales

* **Gestión de Clientes:** Registro y consulta de perfiles de usuario.
* **Cuentas de Ahorro:** Creación de cuentas vinculadas a clientes con manejo de saldos en tiempo real.
* **Operaciones Bancarias:** * Transferencias entre cuentas (con validación de fondos).
    * Recargas y depósitos.
* **Historial de Movimientos:** Consulta detallada de transacciones por cuenta (débitos/créditos).
* **Integridad de Datos:** Implementación de transaccionalidad para asegurar que las operaciones financieras sean atómicas (o se completan ambas partes o no se hace nada).

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue un patrón de **Arquitectura en Capas**, garantizando una clara separación de responsabilidades:

1.  **API Layer (Controllers):** Gestiona las peticiones HTTP y define los endpoints REST.
2.  **Service Layer (Lógica de Negocio):** Donde reside la "inteligencia" del banco (validaciones de saldo, reglas de negocio).
3.  **Data Access Layer (Repository):** Comunicación con la base de datos mediante Spring Data JPA.
4.  **Model Layer (Entities/DTOs):** Representación de los datos y transferencia de información entre capas.
   
---

## 🛠️ Tecnologías Utilizadas

* Java
* Spring Boot
* Base de datos SQL relacional (MySQL)
* Postman (pruebas de endpoints)
* JSON (intercambio de datos)

---

## 📑 API Endpoints

La API sigue las convenciones **RESTful**. A continuación se detallan los recursos principales:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/clientes` | Registra un nuevo cliente en el sistema. |
| `POST` | `/api/cuentas` | Crea una nueva cuenta de ahorro vinculada a un cliente. |
| `POST` | `/api/cuentas/transferir` | Ejecuta una transferencia entre cuentas (Lógica transaccional). |
| `GET` | `/api/cuentas/{numeroCuenta}/historial` | Consulta el historial de depósitos y retiros de una cuenta. |

---
## 📊 Modelo de Datos (ER)

El sistema se basa en un modelo relacional diseñado para garantizar la integridad y consistencia de la información financiera:

* **Cliente (1) ── (N) Cuenta:** Un cliente puede poseer múltiples cuentas (Ahorros, Corriente, etc.).
* **Cuenta (1) ── (N) Movimiento:** Cada cuenta mantiene un historial detallado de sus transacciones.

### 🗺️ Diagrama de Arquitectura
```text
[ Postman ] 
       │
       ▼
[ Controller ] 
       │
       ▼
[ Service ] 
       │
       ▼
[ Repository ] 
       │
       ▼
[ MySQL Database ]



