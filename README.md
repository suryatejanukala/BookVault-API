# BookVault-API Store — Complete Technical Analysis

**1. Project Summary**
**Purpose**: A RESTful Book Store backend application that allows users to manage a catalog of books. It provides secure CRUD operations on books and user account management with JWT-based authentication.

**Problem it solves**: Demonstrates a production-grade Spring Boot REST API with stateless JWT security, MongoDB persistence, role-based access control, structured exception handling, API documentation, containerization, and comprehensive test coverage — serving as a full-stack backend reference implementation.

**2.Project Structure Diagram:**
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


**3. Technology Stack**
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

**4. Architecture**
**Pattern:** Layered (N-Tier) Architecture with MVC
Controller Layer  →  Service Layer  →  Repository Layer  →  MongoDB
     ↕                    ↕
  DTO / Mapper       Exception Handling
     ↕
  Security (JWT Filter + SecurityConfig)
  
Controller Layer (controller/) — handles HTTP requests, delegates to services, returns ResponseEntity<ResponseStructure>
Service Layer (service/ + service/impl/) — business logic, interface + implementation separation
Repository Layer (repository/) — MongoDB data access via MongoRepository
Entity Layer (entity/) — MongoDB documents (Book as Java record, UserInfo as Lombok class)
DTO Layer (dto/) — request/response data transfer objects (Java records with Bean Validation)
Mapper Layer (mapper/) — manual mapping between Entity ↔ DTO (no MapStruct)
Config Layer (config/) — Spring Security, JWT filter, Swagger/OpenAPI
Exception Layer (exception/) — custom exceptions + @RestControllerAdvice global handler
Util Layer (util/) — ResponseStructure wrapper for all API responses
Monitoring Layer (monitoring/) — custom Actuator HealthIndicator

**Design Patterns used:**
Builder pattern — ResponseStructure via Lombok @Builder
Strategy pattern — UserDetailsService implementation
Filter Chain pattern — JwtFilter extends OncePerRequestFilter
DAO/Repository pattern — Spring Data MongoDB repositories
Facade pattern — Service layer abstracts repository complexity


**5. Features Implemented**
User registration with BCrypt password encoding
User login with JWT token generation (30-minute expiry)
JWT-based stateless authentication via Authorization: Bearer <token> header
Role-based access control (ROLE_USER, ROLE_ADMIN)
Full CRUD for books (Create, Read All, Read by ID, Update name, Delete)
Custom MongoDB queries using @Query, @Update, @DeleteQuery annotations
Global exception handling with structured error responses
Bean Validation on all DTOs
Swagger UI / OpenAPI 3 documentation
Spring Boot Actuator with health, info, metrics, env, beans endpoints
Custom HealthIndicator for an external Employee Service
Structured JSON API responses via ResponseStructure wrapper
File-based logging (logs/application.log) + console logging
Docker multi-stage build
Docker Compose for local dev (MongoDB only) and production (app + MongoDB)
Comprehensive unit test suite with JaCoCo coverage reporting

**6. REST APIs**
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

**7. Security**
**Mechanism:** JWT (JSON Web Token) + Spring Security

**SecurityConfig.java** — defines the security filter chain:
CSRF disabled (stateless REST API)
Session management: STATELESS
DaoAuthenticationProvider with BCryptPasswordEncoder
Role-based URL authorization (see REST APIs table above)
HTTP Basic also enabled alongside JWT

**JwtFilter.java** — OncePerRequestFilter:
Extracts Authorization: Bearer <token> header
Validates token via JWTService
Handles ExpiredJwtException → 401 with "JWT token has expired"
Handles SignatureException → 401 with "Invalid JWT signature"
Sets SecurityContextHolder authentication on success

**JWTServiceImpl.java:**
Generates a random HmacSHA256 secret key at startup (in-memory, not persisted)
Token expiry: 30 minutes (1000 * 60 * 30 ms)
Uses JJWT 0.12.6 API
UserInfoUserDetailsServiceImpl.java — implements UserDetailsService, loads user from MongoDB by username
UserInfoUserDetailsMapper.java — implements UserDetails, maps comma-separated roles string to GrantedAuthority list (e.g., "ROLE_ADMIN,ROLE_USER")
Password storage: BCrypt via BCryptPasswordEncoder


