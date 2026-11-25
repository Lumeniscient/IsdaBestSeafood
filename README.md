# IsdaBest Seafood — Point of Sale System

## Overview

**IsdaBest Seafood POS** is a Java-based Point of Sale system designed for a seafood eatery. 
Built using Object-Oriented Programming (OOP) principles and JavaFX with FXML Scene Builder, 
the system provides a simple, efficient, and user-friendly solution for managing day-to-day 
sales operations — from processing customer orders to tracking inventory and monitoring revenue.

The system supports two user roles — **Admin** and **Cashier (Staff)** — each with tailored 
access and responsibilities to ensure organized and secure store operations.

---

## Features

### 🔐 Role-Based Access Control

| Role  | Access |
|-------|--------|
| **Admin** | Dashboard, Menu Management, New Order, Sales Report |
| **Staff** | Dashboard, New Order only |

### Dashboard
- Displays total number of orders and total sales revenue for the day
- Shows current date on login

### New Order
- Browse available products with name, price, and stock
- Add items to an order list with specified quantities
- Stock validation (prevents over-ordering)
- Confirm orders which are then saved to transaction history

### Menu Management *(Admin only)*
- Add new seafood products (name, price, stock)
- Edit existing product details
- Delete products from inventory
- Search products by name using **binary search**
- Inventory is automatically sorted alphabetically after every change

### Sales Report *(Admin only)*
- View full transaction history with timestamps
- Displays itemized order details per transaction
- Shows total earnings and total number of orders

---

## OOP Concepts Applied

| Concept | Where It's Used |
|--------|-----------------|
| **Inheritance** | `userAdmin`, `userStaff` extend `user`; controllers extend `userManager` |
| **Abstraction** | `transactions` is an abstract class with an abstract `computeTotal()` method |
| **Encapsulation** | All models (`product`, `orderItem`, `orders`) use private fields with getters/setters |
| **Polymorphism** | `user` subclasses ('userAdmin', 'userStaff') override 'accessDashboard()' to load the same screen with role-specific behavior |

---

## CRUD Operations

| Operation | Module | Description |
|-----------|--------|-------------|
| **Create** | Menu, New Order | Add products to inventory; place and confirm customer orders |
| **Read** | Dashboard, Sales, Menu | View product list, transaction history, earnings summaries |
| **Update** | Menu | Edit product name, price, and stock |
| **Delete** | Menu, New Order | Remove products from inventory; remove items from an active order |

---

## Tech Stack

| Technology | Details |
|-----------|---------|
| **Language** | Java 8 |
| **UI Framework** | JavaFX 17 (FXML + Scene Builder) |
| **Build Tool** | Maven |
| **IDE** | IntelliJ IDEA |

---

## Default Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | `Admin` | `A123` |
| Staff | `Staff` | `S123` |

---

## Developers

**Group Kuatro Kanto**

| Name | Role |
|------|------|
| Harry Davies | Developer |
| Mark Laurence Madreña | Developer |
| Jonn Lester Gonzales | Developer |
| R-jay Salvacion | Developer |
