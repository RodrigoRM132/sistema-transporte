# 🚛 Sistema de Transporte y Facturación

Sistema backend para la gestión de operaciones de una empresa de transporte de carga, desarrollado con **Java y Spring Boot**, utilizando **MySQL** como sistema de gestión de base de datos.

El proyecto está orientado a la administración de clientes, vehículos, conductores, servicios de transporte, viajes, facturación y pagos mediante una API REST.

## 📌 Descripción

El objetivo del sistema es centralizar la información y los procesos principales relacionados con las operaciones de transporte y facturación.

El proyecto permite trabajar con diferentes módulos independientes, siguiendo una arquitectura organizada por capas y aplicando principios de separación de responsabilidades.

Se encuentra actualmente en desarrollo y continuará incorporando nuevas funcionalidades y mejoras.

## 🎯 Objetivos

* Gestionar clientes.
* Gestionar vehículos.
* Gestionar conductores.
* Registrar servicios de transporte.
* Gestionar viajes.
* Gestionar facturas.
* Registrar pagos.
* Implementar autenticación y autorización mediante JWT.
* Exponer funcionalidades mediante una API REST.
* Aplicar una arquitectura organizada por capas.
* Documentar los endpoints mediante OpenAPI/Swagger.
* Mejorar progresivamente las pruebas y la calidad del código.

## 🛠️ Tecnologías utilizadas

| Tecnología        | Uso                                 |
| ----------------- | ----------------------------------- |
| Java 21           | Lenguaje de programación            |
| Spring Boot       | Desarrollo del backend              |
| Spring Data JPA   | Persistencia de datos               |
| Hibernate         | ORM                                 |
| Spring Security   | Seguridad                           |
| JWT               | Autenticación mediante tokens       |
| MySQL             | Base de datos                       |
| Maven             | Gestión del proyecto y dependencias |
| Swagger / OpenAPI | Documentación de la API             |
| Git               | Control de versiones                |
| GitHub            | Repositorio                         |

## 🏗️ Arquitectura

El proyecto está organizado mediante una arquitectura por capas y módulos.

### Capas principales

* **Controller:** recibe las solicitudes HTTP y expone los endpoints REST.
* **Service:** contiene la lógica de negocio.
* **Repository:** gestiona el acceso a la base de datos.
* **Domain:** contiene las entidades y enumeraciones.
* **DTO:** define los objetos utilizados para solicitudes y respuestas.
* **Security:** contiene la configuración relacionada con autenticación y autorización.
* **Config:** contiene configuraciones generales.
* **Exception:** centraliza el manejo de excepciones.

## 📁 Estructura del proyecto

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── sistema/
│   │           └── rafael/
│   │               ├── admin/
│   │               ├── cliente/
│   │               │   ├── controller/
│   │               │   ├── domain/
│   │               │   ├── dto/
│   │               │   ├── repository/
│   │               │   └── service/
│   │               ├── conductor/
│   │               ├── config/
│   │               │   └── exception/
│   │               ├── factura/
│   │               ├── pago/
│   │               ├── security/
│   │               ├── servicio/
│   │               ├── usuario/
│   │               ├── vehiculo/
│   │               ├── viaje/
│   │               └── RafaelApplication.java
│   │
│   └── resources/
│       ├── application-example.properties
│       └── application.properties
│
└── test/
    └── java/
```

> `application.properties` contiene configuración local y está excluido del repositorio para evitar publicar credenciales.

## 📦 Módulos

### 👤 Usuarios y autenticación

Módulo encargado de la gestión de usuarios y del proceso de autenticación.

Incluye componentes relacionados con:

* Registro de usuarios.
* Inicio de sesión.
* Roles.
* Autenticación mediante JWT.
* Protección de endpoints mediante Spring Security.

### 👥 Clientes

Módulo destinado a la gestión de los clientes de la empresa.

Incluye operaciones para registrar, consultar, actualizar y eliminar clientes según las reglas definidas por la aplicación.

### 🚛 Vehículos

Módulo destinado a la gestión de los vehículos utilizados en las operaciones de transporte.

### 👷 Conductores

Módulo destinado a administrar la información de los conductores asociados a las operaciones de transporte.

### 📦 Servicios de transporte

Módulo encargado de registrar y gestionar los servicios de transporte solicitados por los clientes.

### 🛣️ Viajes

Módulo destinado a gestionar los viajes relacionados con los servicios de transporte.

### 🧾 Facturación

Módulo encargado de la gestión de facturas asociadas a los servicios realizados.

Incluye estructuras relacionadas con los estados de las facturas y filtros de consulta.

### 💳 Pagos

Módulo destinado al registro y gestión de los pagos relacionados con las operaciones de facturación.

## 🔐 Seguridad

La aplicación utiliza **Spring Security** y **JWT (JSON Web Tokens)** para implementar autenticación y autorización.

El flujo general es:

```text
Cliente
   │
   │ Login
   ▼
AuthController
   │
   ▼
AuthService
   │
   ▼
Validación de credenciales
   │
   ▼
Generación del JWT
   │
   ▼
Cliente recibe el token
   │
   │ Authorization: Bearer <token>
   ▼
JwtAuthenticationFilter
   │
   ▼
Spring Security
   │
   ▼
Endpoint protegido
```

## 🗄️ Base de datos

El proyecto utiliza **MySQL**.

Base de datos utilizada durante el desarrollo:

```text
facturacion_transporte
```

La configuración de la conexión se realiza mediante:

```text
src/main/resources/application.properties
```

Las credenciales locales no deben publicarse en GitHub.

Para facilitar la configuración del proyecto se incluye:

```text
src/main/resources/application-example.properties
```

Este archivo sirve como plantilla para configurar el entorno local.

## 📚 Documentación de la API

El proyecto utiliza **Swagger / OpenAPI** para documentar y probar los endpoints disponibles.

Una vez iniciada la aplicación, la interfaz de Swagger puede estar disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

> La dirección puede variar dependiendo de la configuración utilizada.

## ⚙️ Requisitos

Para ejecutar el proyecto localmente se recomienda disponer de:

* Java 21
* MySQL 8
* Git
* Maven o Maven Wrapper
* Un IDE compatible con Java

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/RodrigoRM132/sistema-transporte.git
```

### 2. Entrar al proyecto

```bash
cd sistema-transporte
```

### 3. Crear la base de datos

En MySQL:

```sql
CREATE DATABASE facturacion_transporte;
```

### 4. Configurar la aplicación

Crear:

```text
src/main/resources/application.properties
```

utilizando como referencia:

```text
src/main/resources/application-example.properties
```

Ejemplo:

```properties
spring.application.name=facturacion-transporte

spring.datasource.url=jdbc:mysql://localhost:3306/facturacion_transporte?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Lima
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> No utilizar credenciales reales dentro del repositorio.

### 5. Ejecutar la aplicación

En Windows:

```bash
.\mvnw.cmd spring-boot:run
```

También puede ejecutarse desde el IDE utilizando:

```text
RafaelApplication.java
```

## 🧪 Pruebas

El proyecto cuenta actualmente con una estructura inicial de pruebas dentro de:

```text
src/test/
```

Las pruebas pueden ejecutarse mediante:

```bash
.\mvnw.cmd test
```

La cobertura de pruebas continuará ampliándose durante el desarrollo.

## 🔄 Estado del proyecto

🚧 **Proyecto en desarrollo**

### ✅ Implementado

* [x] Estructura base del proyecto Spring Boot
* [x] Configuración de Maven
* [x] Configuración de conexión con MySQL
* [x] Persistencia mediante Spring Data JPA / Hibernate
* [x] Módulo de clientes
* [x] Módulo de vehículos
* [x] Módulo de conductores
* [x] Módulo de servicios de transporte
* [x] Módulo de viajes
* [x] Módulo de facturación
* [x] Módulo de pagos
* [x] Módulo de usuarios
* [x] Autenticación mediante JWT
* [x] Configuración de Spring Security
* [x] Manejo global de excepciones
* [x] DTOs para solicitudes y respuestas
* [x] Documentación mediante OpenAPI / Swagger

### 🚧 En desarrollo

* [ ] Ampliación de pruebas unitarias
* [ ] Pruebas de integración
* [ ] Mejoras de validaciones y reglas de negocio
* [ ] Mejoras en seguridad
* [ ] Optimización de consultas y rendimiento
* [ ] Mejoras de documentación de la API

### 📋 Pendiente

* [ ] Desarrollo de frontend
* [ ] Dashboard de información
* [ ] Reportes de operaciones
* [ ] Reportes de facturación y pagos
* [ ] Despliegue en un entorno cloud
* [ ] Configuración para diferentes entornos (desarrollo, pruebas y producción)

## 🗺️ Roadmap

El desarrollo del proyecto seguirá aproximadamente las siguientes etapas:

```text
Backend
  │
  ├── Gestión de entidades
  ├── Reglas de negocio
  ├── Seguridad
  ├── Validaciones
  └── Pruebas
          │
          ▼
Documentación
          │
          ▼
Frontend
          │
          ▼
Reportes y Dashboard
          │
          ▼
Despliegue
```

## 🔒 Seguridad de la configuración

Las configuraciones que contienen información sensible, como contraseñas de bases de datos, se mantienen fuera del repositorio.

El archivo:

```text
application.properties
```

está excluido mediante `.gitignore`.

En su lugar, el proyecto incluye:

```text
application-example.properties
```

como referencia para configurar el entorno local.

## 📌 Próximas mejoras

Algunas mejoras previstas para futuras versiones son:

* Implementar una interfaz web para consumir la API.
* Crear un dashboard administrativo.
* Incorporar reportes relacionados con viajes, facturación y pagos.
* Incrementar la cobertura de pruebas.
* Mejorar las validaciones de negocio.
* Incorporar configuraciones específicas para desarrollo y producción.
* Preparar el proyecto para despliegue en la nube.
* Mejorar el rendimiento y la documentación.

## 👨‍💻 Autor

**Rodrigo Rafael Melgarejo**

Egresado de Ingeniería de Sistemas.

Proyecto desarrollado como iniciativa personal para fortalecer conocimientos en desarrollo backend, arquitectura de aplicaciones, APIs REST, persistencia de datos y seguridad con Java y Spring Boot.

---

⭐ Si el proyecto resulta interesante, puedes revisar su evolución directamente en el repositorio de GitHub.

**Repositorio:**
https://github.com/RodrigoRM132/sistema-transporte
