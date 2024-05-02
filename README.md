


# Category Product Service

This is a Spring Boot application for managing categories and products. It provides APIs for creating, updating, deleting, and retrieving categories and products. The application is designed with RESTful principles and uses JPA/Hibernate for data persistence.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Endpoints](#endpoints)
- [Error Handling](#error-handling)

## Features
- **Category Management**: CRUD operations for categories.
- **Product Management**: CRUD operations for products associated with categories.
- **Pagination**: APIs support pagination to efficiently handle large datasets.
- **Error Handling**: Custom exceptions and global exception handling for better error responses.
- **RESTful APIs**: Follows RESTful principles for clean and consistent API design.

## Technologies Used
- **Spring Boot**: Framework for building Java applications.
- **Spring Data JPA**: Simplifies data access and manipulation using JPA.
- **H2 Database**: In-memory database for storing data during development.
- **Lombok**: Library to reduce boilerplate code with annotations like @Getter and @Setter.

## Endpoints
### Categories
- **GET /api/categories**: Get all categories (supports pagination).
- **GET /api/categories/{id}**: Get category by ID.
- **POST /api/categories**: Create a new category.
- **PUT /api/categories/{id}**: Update a category.
- **DELETE /api/categories/{id}**: Delete a category.

### Products
- **GET /api/products**: Get all products (supports pagination).
- **GET /api/products/{id}**: Get product by ID.
- **POST /api/products**: Create a new product.
- **PUT /api/products/{id}**: Update a product.
- **DELETE /api/products/{id}**: Delete a product.

## Error Handling
The application provides custom exceptions for scenarios like Category ID not found and Product ID not found. Global exception handling is implemented using @RestControllerAdvice to handle these exceptions and provide consistent error responses.
