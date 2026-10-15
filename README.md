# 🏦 SBA Loan Origination & Prequalification Engine

A production-grade reference microservice for SBA 7(a) small business loan intake, eligibility evaluation, and workflow automation.

Built specifically with the **LoanBud core technology stack**: **Java 21**, **Spring Boot 3.3**, **PostgreSQL 15**, **Liquibase**, **MapStruct**, **Caffeine**, and **OpenAPI 3.0**.

---

## 🛠️ Architecture & Tech Stack

- **Core Framework:** Java 21 & Spring Boot 3.3.x
- **Database & Persistence:** PostgreSQL 15, Spring Data JPA, Liquibase Migrations
- **APIs & Contracts:** RESTful APIs, OpenAPI 3.0 (Swagger UI), MapStruct Mappers
- **Caching & Resilience:** Caffeine In-Memory Cache
- **Validation & Security:** Spring Validation, Spring Security
- **Containerization:** Docker & Docker Compose

---

## 🚀 Local Setup & Quickstart

### Prerequisites
- JDK 21
- Docker & Docker Compose
- Maven 3.9+

### 1. Start PostgreSQL Database
```bash
docker-compose up -d