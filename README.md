# BookVault-API Store — Complete Technical Analysis

**1. Project Summary**
**Purpose**: A RESTful Book Store backend application that allows users to manage a catalog of books. It provides secure CRUD operations on books and user account management with JWT-based authentication.

**Problem it solves**: Demonstrates a production-grade Spring Boot REST API with stateless JWT security, MongoDB persistence, role-based access control, structured exception handling, API documentation, containerization, and comprehensive test coverage — serving as a full-stack backend reference implementation.

**2. Technology Stack**
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

