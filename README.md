📦 Milestone 3 – Order Management API
=====================================

🚀 Project Overview
======================

This project implements a RESTful API for order management with stock handling, validation, and transaction management.
Users can create products, place orders, and manage inventory efficiently. The system ensures data integrity using Spring Boot, JPA, and MySQL.

🛠️ Features
============

Product Management
-----------------

Create, Read, Update, Delete (CRUD) operations for products.

Stock and price management.
Duplicate product name prevention.
Validation for all input fields.

Order Management
-----------------

Place orders for one or more products.

Automatically updates stock based on order quantity.

Validates:

Product exists
Quantity ≥ 1
Stock availability

Transaction-safe: all operations rollback on failure.

Security & Error Handling

Custom exceptions for:

Product not found ❌

Insufficient stock ⚠️

Invalid quantity ❌

Product already exists ⚠️

Global exception handler provides consistent JSON responses.

🧾 API Endpoints
Products
Method	Endpoint	Description
POST	/api/products	Create a new product
GET	/api/products	Retrieve all products
GET	/api/products/{id}	Get product by ID
PUT	/api/products/{id}	Update product details
DELETE	/api/products/{id}	Delete product
Orders
Method	Endpoint	Description
POST	/api/orders	Place a new order
GET	/api/orders/{id}	Get order by ID
📦 JSON Request Examples
Create Product
{
  "name": "Laptop",
  "price": 75000,
  "stock": 10
}

Place Order
{
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}

🔄 Transaction Boundaries
============================

Atomicity: Each order placement is handled in a single transaction.

Consistency: Stock is updated only if the order is successfully placed.

Isolation: Prevents concurrent stock updates conflicts.

Durability: Order and stock updates are persisted in MySQL.
