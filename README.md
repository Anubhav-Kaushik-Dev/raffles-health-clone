## Raffles Health Insurance(Claim module)-Clone

A backend-focused Spring Boot application implementing the Claim Management module of a health insurance platform.

**Features Implemented**

- RESTful APIs developed using Spring Boot following layered architecture
- Claim management module with CRUD operations
- Pagination 
- Dynamic filtering of claims (based on status and customer ID)
- Sorting support on claim attributes (claimAmount, etc.)
- Global exception handling
- Field-level request validation
- DTO-based response mapping for clean API contracts
- Role Based Access Control(RBAC) using Spring Security
- Containerized the Spring Boot application using Docker and published image to dockerhub
- Deployed it on AWS EC2 for cloud-based access.

 **Database Structure**

Users table,
Roles table,
Permissions table,
user_roles & role_permissions join tables

NOTE:For Bootstrapping first time,manually create an admin,Role and Permission record using SQL query and use permitAll() for /add-admin end point in SecurityFilterChain.Second time onward,/add-admin must be authenticated() in SecurityFilterChain.

**Tech Stack**

Backend: Java 8, Spring Boot, Spring MVC |
Security: Spring Security (RBAC) |
Persistence: Spring Data JPA ,Hibernate |
Database: MySQL |
Build Tool: Maven |
API Documentation & Testing: Swagger, Postman |
Containerization: Docker |
Cloud: AWS EC2

**How to Run Locally**
**Clone repository**
git clone https://github.com/Anubhav-Kaushik-Dev/raffles-health-clone.git

**Build project**
mvn clean install

**Run application**
mvn spring-boot:run

**Author**
**Anubhav Kaushik**
Java Backend Developer
LinkedIn: https://linkedin.com/in/anubhav-kaushik-dev

  
