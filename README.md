# Microservices Booking System

This project demonstrates a microservices architecture built with Spring Boot.

## Architecture

Client → API Gateway → Microservices

Services included:

### Auth Service
Handles authentication and JWT generation.

Endpoints:
POST /auth/register
POST /auth/login

Port: 8080

### Booking Service
Handles booking APIs and role-based authorization.

Endpoints:
GET /bookings
GET /bookings/admin

Port: 8081

### API Gateway
Routes requests and validates JWT tokens.

Port: 8082

## Security Flow

1. User registers or logs in
2. Auth service generates JWT token
3. Client sends JWT in Authorization header
4. API Gateway validates token
5. Booking service authorizes request based on role

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Cloud Gateway
- Maven

## Run the Project

Start services in this order:

1. Auth Service
2. Booking Service
3. API Gateway
