# Library API

A RESTful API for managing a library system — authors, books, and their relationships — built with Spring Boot 4, Java 17, JPA (Hibernate) and MySQL.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.5 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Validation | Spring Boot Validation |
| API Docs | SpringDoc OpenAPI (Swagger UI) 2.8.6 |
| Build Tool | Maven |

---

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+

---

## Setup & Configuration

### 1. Clone the repository

```bash
git clone https://github.com/filatok/Library-api.git
cd Library-api
```

### 2. Create the database

Run the provided SQL script to create and seed the database:

```bash
mysql -u your_username -p < 01_CreateDatabase.sql
```

### 3. Configure the datasource

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
```
Note: ddl-auto=validate means the schema must already exist and match the entity mappings. Create the schema before starting the app, or switch this to update during development.

Update the username/password to match your local MySQL setup.

### 4. Build and run

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will start at: `http://localhost:8080`

---

## API Documentation

Once the app is running, open Swagger UI in your browser:

```
http://localhost:8080/swagger-ui/index.html
```

This provides an interactive interface to explore and test all available endpoints.

---

## Project Structure

```
library-api/
├── src/
│   ├── main/
│   │   ├── java/gr/filatok/library_api/
│   │   │   ├── api/
│   │   │   │   ├── configurations/    # CorsConfiguration
│   │   │   │   ├── controllers/       # AuthorController, BookController
│   │   │   │   └── ApiExceptionHandler.java
│   │   │   ├── business/
│   │   │   │   ├── dtos/              # AuthorDto, BookDto
│   │   │   │   │   └── exceptions/    # EntityNotFoundException
│   │   │   │   └── services/          # AuthorService, BookService
│   │   │   ├── domain/
│   │   │   │   └── models/
│   │   │   │       ├── entities/      # Author, Book
│   │   │   │       └── repositories/  # AuthorRepository, BookRepository
│   │   │   └── LibraryApiApplication.java
```


## CORS Configuration

CORS is open to all origins and methods (/**), configured in apiLayer/configurations/CorsConfiguration.java. Tighten this before going to production.

---

## Testing

Test suites are split per layer. All tests use **JUnit 5** and **AssertJ**.

DomainLayerTests
BusinessLayerTests
ApiLayerTests 
LibraryApiApplicationTests 
---

### Running the Tests

```bash
./mvnw test
```

Or on Windows:

```bash
mvnw.cmd test
```

---
