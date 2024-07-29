# Student Service with JWT Authentication

This project demonstrates the implementation of JWT (JSON Web Token) authentication in a Spring Boot application for a Student Service. It showcases a one-to-many relationship between students and courses, integrating Spring Security for authentication and authorization.

## Project Overview

The Student Service manages student information and their course enrollments. Each student must log in to access protected resources, with authentication handled through Spring Security and JWTs.

### Key Features

- JWT-based authentication
- Student and Course management
- One-to-many relationship between Students and Courses
- Role-based access control
- RESTful API endpoints for student and course operations

## Technologies Used

- Spring Boot 3.x
- Spring Security
- JSON Web Tokens (JWT)
- Spring Data JPA
- Maven

## Security

This project uses Spring Security for authentication and authorization. To obtain JWT token, user must authenticate itself in "/auth/login" endpoint. JWTs are used to secure the API endpoints. Users must include the JWT in the Authorization header for authenticated requests.

## Database

For now data was initialized and stored using arrays for in-memory data storage. Future development would include using H2 in-memory database. H2 in-memory is a good practice if you do not want to download an database in your local. You can access the H2 console at `http://localhost:8080/h2-console` when the application is running.
