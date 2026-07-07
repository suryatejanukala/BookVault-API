# BookVault-API Store — Complete Technical Analysis

## 1. Project Summary

- **Purpose**
  - A RESTful Book Store backend application that enables users to manage a catalog of books. It provides secure CRUD operations for books along with user registration and authentication using JWT-based security.

- **Problem It Solves**
  - Demonstrates a production-grade Spring Boot REST API featuring:
    - Stateless JWT authentication and authorization
    - MongoDB persistence
    - Role-based access control (RBAC)
    - Global exception handling with structured error responses
    - Bean Validation for request validation
    - Interactive API documentation using Swagger/OpenAPI
    - Docker-based containerization
    - Comprehensive unit testing with JaCoCo code coverage reporting

  - Serves as a reference implementation for building secure, scalable, and production-ready backend applications using Spring Boot and MongoDB.


## 2.Project Structure Diagram:

```
src/
│   ├── main/
│   │   ├── java/com/thinkconstructive/book_store/
│   │   │   │
│   │   │   ├── config/                          ← Security & Swagger Configuration
│   │   │   │   ├── JwtFilter.java               (OncePerRequestFilter - JWT validation)
│   │   │   │   ├── SecurityConfig.java          (SecurityFilterChain, AuthProvider, RBAC)
│   │   │   │   └── SwaggerConfig.java           (OpenAPI metadata)
│   │   │   │
│   │   │   ├── controller/                      ← REST Controllers
│   │   │   │   ├── BookController.java          (CRUD endpoints: /book-store/**)
│   │   │   │   └── UserInfoController.java      (Auth endpoints: /user-info/**)
│   │   │   │
│   │   │   ├── dto/                             ← Data Transfer Objects (Java Records)
│   │   │   │   ├── BookDto.java                 (with Bean Validation)
│   │   │   │   └── UserInfoDto.java             (with Bean Validation)
│   │   │   │
│   │   │   ├── entity/                          ← MongoDB Documents
│   │   │   │   ├── Book.java                    (Java record, @Document "books")
│   │   │   │   └── UserInfo.java                (Lombok @Data class)
│   │   │   │
│   │   │   ├── exception/                       ← Exception Handling
│   │   │   │   ├── BookNotFoundException.java
│   │   │   │   ├── ErrorResponse.java           (Java record)
│   │   │   │   ├── GlobalExceptionHandler.java  (@RestControllerAdvice)
│   │   │   │   ├── UserAlreadyExistsException.java
│   │   │   │   └── UserNotFoundException.java
│   │   │   │
│   │   │   ├── mapper/                          ← Entity ↔ DTO Mappers
│   │   │   │   ├── BookMapper.java              (static utility)
│   │   │   │   ├── UserInfoMapper.java          (static utility)
│   │   │   │   └── UserInfoUserDetailsMapper.java (implements UserDetails)
│   │   │   │
│   │   │   ├── monitoring/                      ← Actuator Health Indicators
│   │   │   │   └── Employee.java                (custom HealthIndicator)
│   │   │   │
│   │   │   ├── repository/                      ← Data Access Layer
│   │   │   │   ├── BookRepository.java          (MongoRepository + custom queries)
│   │   │   │   └── UserInfoRepository.java      (MongoRepository)
│   │   │   │
│   │   │   ├── service/                         ← Business Logic (Interfaces)
│   │   │   │   ├── BookService.java
│   │   │   │   ├── JWTService.java
│   │   │   │   ├── UserInfoService.java
│   │   │   │   └── impl/                        ← Service Implementations
│   │   │   │       ├── BookServiceImpl.java
│   │   │   │       ├── JWTServiceImpl.java
│   │   │   │       ├── UserInfoServiceImpl.java
│   │   │   │       └── UserInfoUserDetailsServiceImpl.java
│   │   │   │
│   │   │   ├── util/                            ← Utility Classes
│   │   │   │   └── ResponseStructure.java       (Unified API response wrapper)
│   │   │   │
│   │   │   └── BookStoreApplication.java        ← Main Entry Point
│   │   │
│   │   └── resources/
│   │       ├── static/                          (empty - no frontend)
│   │       ├── templates/                       (empty - no Thymeleaf)
│   │       └── application.properties           (logging, actuator, external service config)
```

## 3. Technology Stack

| Technology | Version / Detail | Source |
|------------|------------------|--------|
| Java | 17 | `pom.xml` → `<java.version>17</java.version>` |
| Spring Boot | 3.4.1 | `pom.xml` → `spring-boot-starter-parent` |
| Spring Web (MVC) | via Spring Boot 3.4.1 | `spring-boot-starter-web` |
| Spring Data MongoDB | via Spring Boot 3.4.1 | `spring-boot-starter-data-mongodb` |
| Spring Security | via Spring Boot 3.4.1 | `spring-boot-starter-security` |
| Spring Validation | via Spring Boot 3.4.1 | `spring-boot-starter-validation` |
| Spring Boot Actuator | via Spring Boot 3.4.1 | `spring-boot-starter-actuator` |
| Spring Boot DevTools | via Spring Boot 3.4.1 | `spring-boot-devtools` |
| Spring Boot Docker Compose | via Spring Boot 3.4.1 | `spring-boot-docker-compose` |
| JJWT (JWT Library) | 0.12.6 | `jjwt-api`, `jjwt-impl`, `jjwt-jackson` |
| SpringDoc OpenAPI (Swagger UI) | 2.8.6 | `springdoc-openapi-starter-webmvc-ui` |
| Lombok | via Spring Boot BOM | `lombok` |
| Maven | 3.9 (Wrapper) | `mvnw`, `.mvn/wrapper/maven-wrapper.properties` |
| JaCoCo | 0.8.12 | `pom.xml` → `jacoco-maven-plugin` |
| JUnit 5 | via `spring-boot-starter-test` | Test classes |
| Mockito | via `spring-boot-starter-test` | Test classes |
| Spring Security Test | via `spring-security-test` | Test classes |
| MongoDB | Latest (Docker Image) | `compose.yaml` |
| Docker | Multi-stage Build | `Dockerfile` |
| Docker Compose | Development & Production Profiles | `compose.yaml`, `compose.prod.yaml` |
| Amazon Corretto JDK | 17 (Base Image) | `Dockerfile` → `amazoncorretto:17` |
| Logback | via Spring Boot (Default) | `application.properties` logging configuration |

## 4. Architecture

**Pattern:** Layered (N-Tier) Architecture with MVC
```
Controller Layer  →  Service Layer  →  Repository Layer  →  MongoDB
     ↕                    ↕
  DTO / Mapper       Exception Handling
     ↕
  Security (JWT Filter + SecurityConfig)
```
  
## Architecture Overview

- **Controller Layer (`controller/`)**
  - Handles HTTP requests.
  - Delegates business logic to the service layer.
  - Returns standardized `ResponseEntity` responses.

- **Service Layer (`service/` & `service/impl/`)**
  - Contains the application's business logic.
  - Uses interface and implementation separation for better maintainability and testability.

- **Repository Layer (`repository/`)**
  - Provides data access using Spring Data MongoDB.
  - Extends `MongoRepository` for CRUD and custom database operations.

- **Entity Layer (`entity/`)**
  - Defines MongoDB document models.
  - `Book` is implemented as a Java Record.
  - `UserInfo` is implemented as a Lombok `@Data` class.

- **DTO Layer (`dto/`)**
  - Defines request and response data transfer objects.
  - Uses Java Records with Bean Validation annotations.

- **Mapper Layer (`mapper/`)**
  - Performs manual mapping between Entities and DTOs.
  - Does not use MapStruct.

- **Configuration Layer (`config/`)**
  - Configures Spring Security.
  - Implements JWT authentication.
  - Configures Swagger/OpenAPI documentation.

- **Exception Layer (`exception/`)**
  - Defines custom exception classes.
  - Provides centralized exception handling using `@RestControllerAdvice`.

- **Utility Layer (`util/`)**
  - Contains the `ResponseStructure` class for standardized API responses.

- **Monitoring Layer (`monitoring/`)**
  - Implements custom Spring Boot Actuator `HealthIndicator`s for application monitoring.

---

## Design Patterns Used

- **Builder Pattern**
  - Used in `ResponseStructure` via Lombok `@Builder`.

- **Strategy Pattern**
  - Used for the `UserDetailsService` implementation in Spring Security.

- **Filter Chain Pattern**
  - Implemented using `JwtFilter` extending `OncePerRequestFilter`.

- **DAO/Repository Pattern**
  - Implemented through Spring Data MongoDB repositories.

- **Facade Pattern**
  - Service layer abstracts repository interactions and business logic.


## 5. Features Implemented

- User registration with **BCrypt** password encoding.
- User authentication with **JWT token generation** (30-minute expiration).
- Stateless authentication using the **`Authorization: Bearer <token>`** header.
- Role-based access control (**`ROLE_USER`** and **`ROLE_ADMIN`**).
- Complete CRUD operations for books:
  - Create a book
  - Retrieve all books
  - Retrieve a book by ID
  - Update a book's name
  - Delete a book
- Custom MongoDB queries using:
  - `@Query`
  - `@Update`
  - `@DeleteQuery`
- Global exception handling with standardized error responses.
- Bean Validation for all request DTOs.
- Interactive API documentation using **Swagger UI / OpenAPI 3**.
- Spring Boot Actuator endpoints for:
  - Health
  - Info
  - Metrics
  - Environment
  - Beans
- Custom `HealthIndicator` for monitoring an external Employee Service.
- Standardized JSON API responses using the `ResponseStructure` wrapper.
- File-based logging (`logs/application.log`) along with console logging.
- Docker multi-stage build for optimized container images.
- Docker Compose support for:
  - Local development (MongoDB)
  - Production (Application + MongoDB)
- Comprehensive unit testing with **JUnit 5**, **Mockito**, and **JaCoCo** code coverage reporting.



## 6. REST APIs

### Book Management APIs
> **Base Path:** `/book-store` — BookController.java

| Method | Endpoint | Authentication | Role | Description |
|--------|----------|----------------|------|-------------|
| GET | `/book-store/welcome` | No | Public | Returns a welcome message. |
| GET | `/book-store/{bookId}` | Yes | USER / ADMIN | Fetch a book by its ID. |
| GET | `/book-store/` | Yes | USER / ADMIN | Retrieve all books. |
| POST | `/book-store/` | Yes | ADMIN | Create a new book. |
| PUT | `/book-store/` | Yes | ADMIN | Update a book's name by ID. |
| DELETE | `/book-store/{bookId}` | Yes | ADMIN | Delete a book by its ID. |

### User Authentication APIs
> **Base Path:** `/user-info`

| Method | Endpoint | Authentication | Description |
|--------|----------|----------------|-------------|
| POST | `/user-info/register` | No | Register a new user. |
| POST | `/user-info/login` | No | Authenticate the user and receive a JWT access token. |


## 7. Security

- **Authentication Mechanism**
  - JWT (JSON Web Token) with Spring Security.

- **`SecurityConfig.java`**
  - Configures the Spring Security filter chain.
  - Disables CSRF for stateless REST APIs.
  - Uses **`SessionCreationPolicy.STATELESS`**.
  - Configures `DaoAuthenticationProvider` with `BCryptPasswordEncoder`.
  - Implements role-based URL authorization.
  - Supports both JWT authentication and HTTP Basic authentication.

- **`JwtFilter.java`**
  - Extends `OncePerRequestFilter`.
  - Extracts the JWT from the `Authorization: Bearer <token>` header.
  - Validates the token using `JWTService`.
  - Handles `ExpiredJwtException` by returning **HTTP 401 Unauthorized** with the message **"JWT token has expired"**.
  - Handles `SignatureException` by returning **HTTP 401 Unauthorized** with the message **"Invalid JWT signature"**.
  - Sets the authenticated user in the `SecurityContextHolder` after successful validation.

- **`JWTServiceImpl.java`**
  - Generates a random **HMAC-SHA256** secret key at application startup.
  - Stores the secret key in memory (not persisted).
  - Generates JWT tokens with a **30-minute expiration**.
  - Uses the **JJWT 0.12.6** library for token creation and validation.

- **`UserInfoUserDetailsServiceImpl.java`**
  - Implements `UserDetailsService`.
  - Loads user details from MongoDB based on the username.

- **`UserInfoUserDetailsMapper.java`**
  - Implements `UserDetails`.
  - Converts comma-separated roles (e.g., `ROLE_ADMIN,ROLE_USER`) into a list of `GrantedAuthority` objects.

- **Password Security**
  - Passwords are securely hashed using `BCryptPasswordEncoder` before being stored in MongoDB.


