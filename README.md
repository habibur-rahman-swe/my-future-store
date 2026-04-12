# Microservices Professional E-Commerce

This is a professional e-commerce platform built using microservices architecture with Spring Boot.

## Overview

The project aims to create a scalable e-commerce system consisting of multiple microservices for handling different aspects like user management, product catalog, orders, payments, etc.

## Current Services

- **E-Commerce Service**: A basic Spring Boot service with hello endpoints. Located in `E-Commerce/` directory.

## Technologies Used

- Java 17
- Spring Boot 3.x
- Maven
- Spring Web
- (Future: Spring Cloud, Eureka, Zuul, etc.)

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6+

### Running the Application

1. Clone the repository.
2. Navigate to the service directory: `cd E-Commerce`
3. Run the application: `mvn spring-boot:run`

The service will start on `http://localhost:8080`.

### API Endpoints

- `GET /api/v1/hello` - Returns "Hello World"
- `POST /api/v1/hello` - Accepts a string body and returns "Hello {name} from POST"

## Architecture

The system follows microservices architecture where each service is independently deployable and scalable.

Future services will include:
- User Service
- Product Service
- Order Service
- Payment Service
- Notification Service

## Contributing

Please follow standard practices for contributions.