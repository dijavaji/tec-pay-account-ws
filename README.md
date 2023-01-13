"# tec-pay-account-ws"
# Technical Interview Proposal

This is my solution for Technical Challenge Here's an overview of the application structure:

## Problem Description
ESPECIFICACIONES FUNCIONALES
• El sistema debe poder realizar una operación de pago (cargo y abono en cuentas) considerando que el servicio recibirá cuenta origen, cuenta destino y monto.
• El sistema debe tener una tabla de cuentas con: número de cuenta y saldo, la misma que será actualizada al realizar la operación de pago.
• El sistema deberá devolver el numero de operación y el saldo final de las cuentas (origen y destino).
• Para realizar una operación se debe autenticar (token jwt).
• Por cada operación realizada, se debe registrar quien hizo la solicitud (auditoría funcional).

ESPECIFICACIONES NO FUNCIONALES
• Framework Spring Boot.
• Opcional: Utilizar programación reactiva (WebFlux/RxJava).
• Utilizar una base de datos SQL o NoSQL (Opcional dockerizada).
• Dockerizar los componentes resultantes.
• La seguridad debe ser a través de JWT.
• Usar Postman o SOAPUI para el consumo de la API.
• Implementar pruebas unitarias.

## Solution Overview
For development used the following technologies.
Development Ide
Spring Tool Suite
Source control
• GIT
Database
• MySQL
Backend
•Java Spring boot
Frontend
Angular 7
Methodology
• Scrum


The tec-pay-account-ws solution contains a project:

tec-pay-account-ws
Is the backend project this project contains all the classes which are used to manipulate the logic business of application, necessary data model and components the rest-api type.

## Available Scripts

In the project directory tec-pay-account-ws , you can run:

## Development server

### `mvn clean install`

Builds the app for production to the `target` folder.\
It correctly bundles jar in production mode and optimizes the build for the best performance.

### `mvn spring-boot:run`
Run Spring Boot applications Navigate to `http://127.0.0.1:8080/`. You have the nexts apis built for creating the operation.

### `mvn test`

### `mvn -q test`

Launches the test runner in the interactive watch mode.\

Test input:

