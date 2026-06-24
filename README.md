# SpotyFruna - EVP 2

## Contexto

SpotyFruna es una plataforma de streaming musical desarrollada bajo una arquitectura de microservicios utilizando Spring Boot, Spring Cloud y Docker.

El sistema permite administrar usuarios, álbumes, canciones, playlists, suscripciones y reproducciones, proporcionando además mecanismos de autenticación, generación de reportes y centralización de configuraciones.

La solución implementa patrones de arquitectura distribuidos mediante API Gateway, Service Discovery (Eureka) y Config Server, facilitando la escalabilidad, mantenibilidad y despliegue de la plataforma.

---

## Créditos

**Integrantes del equipo**

* Adrián López      | adri.lopez@duocuc.cl
* Benjamín Albornoz | be.albornozc@duocuc.cl
* Ignacia Vásquez   | ignac.vasquez@duocuc.cl

---

## Arquitectura

### Microservicios implementados

| Microservicio          | Descripción                                     |
| ---------------------- | ----------------------------------------------- |
| api-gateway            | Punto único de entrada para todos los clientes. |
| eureka-service         | Registro y descubrimiento de servicios.         |
| config-server          | Centralización de configuraciones.              |
| auth-service           | Gestión de autenticación y autorización.        |
| usuarios-service       | Administración de usuarios.                     |
| albumes-service        | Administración de álbumes musicales.            |
| canciones-service      | Administración de canciones.                    |
| playlists-service      | Administración de playlists.                    |
| suscripciones-service  | Gestión de suscripciones de usuarios.           |
| reproducciones-service | Registro de reproducciones musicales.           |
| reportes-service       | Generación de reportes del sistema.             |

### Arquitectura General

Cliente → API Gateway → Microservicios

Servicios de infraestructura:

* Config Server
* Eureka Server
* Base de Datos MySQL
* Docker Compose

---

## Networking

### Rutas principales expuestas por el API Gateway

| Servicio               | Ruta Base                   |
| ---------------------- | --------------------------- |
| Auth Service           | `/api/v1/registros/**`           |
| Usuarios Service       | `/api/v1/usuarios/**`       |
| Álbumes Service        | `/api/v1/albumes/**`        |
| Canciones Service      | `/api/v1/canciones/**`      |
| Playlists Service      | `/api/v1/playlists/**`      |
| Suscripciones Service  | `/api/v1/suscripciones/**`  |
| Reproducciones Service | `/api/v1/reproducciones/**` |
| Reportes Service       | `/api/v1/reportes/**`       |

> Ajustar las rutas según la configuración final definida en el API Gateway.

---

## Accesos

### Swagger Local

| Servicio               | URL                                                                                    |
| ---------------------- | -------------------------------------------------------------------------------------- |
| Auth Service           | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Usuarios Service       | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Álbumes Service        | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Canciones Service      | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Playlists Service      | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Suscripciones Service  | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Reproducciones Service | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| Reportes Service       | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |

### API Gateway

http://localhost:8080

### Eureka Dashboard

http://localhost:8761

---

## Guía de Despliegue

### Requisitos Previos

* Java 21 o superior
* Maven 3.9+
* Docker
* Docker Compose
* MySQL 8.x
* Git

---

## Despliegue Contenerizado (Docker)

### 1. Clonar el repositorio

```bash
git clone https://github.com/l-AndyNic-l/SpotyFruna_EVP_2
cd SpotyFruna
```

### 2. Construir los proyectos

```bash
mvn clean package -DskipTests
```

### 3. Levantar los contenedores

```bash
docker compose up --build
```

### 4. Verificar ejecución

```bash
docker ps
```

### 5. Verificar Eureka

Abrir:

```text
http://localhost:8761
```

Todos los servicios deben aparecer registrados.

### 6. Acceder a Swagger

Ingresar a las URLs correspondientes de cada microservicio.

---

## Despliegue Local / Híbrido

### 1. Crear Base de Datos

Ejecutar:

```sql
scripts/db.sql
```

### 2. Levantar infraestructura

Orden recomendado:

1. Config Server
2. Eureka Service
3. API Gateway
4. Auth Service
5. Usuarios Service
6. Álbumes Service
7. Canciones Service
8. Playlists Service
9. Suscripciones Service
10. Reproducciones Service
11. Reportes Service

### 3. Ejecutar cada microservicio

Desde IntelliJ IDEA:

```text
Run Application
```

o mediante Maven:

```bash
mvn spring-boot:run
```

### 4. Verificar Registro

Abrir:

```text
http://localhost:8761
```

### 5. Probar APIs

Utilizar:

* Swagger UI
* Postman
* API Gateway

---

## Tecnologías Utilizadas

* Java 21
* Spring Boot
* Spring Cloud
* Spring Security
* Spring Data JPA
* MySQL
* Eureka Server
* Config Server
* API Gateway
* Docker
* Docker Compose
* Maven
* JUnit 5
* Mockito

---

## Estructura del Proyecto

```text
SpotyFruna
│
├── api-gateway
├── config-server
├── eureka-service
├── auth-service
├── usuarios-service
├── albumes-service
├── canciones-service
├── playlists-service
├── suscripciones-service
├── reproducciones-service
├── reportes-service
└── docker-compose.yml
```
