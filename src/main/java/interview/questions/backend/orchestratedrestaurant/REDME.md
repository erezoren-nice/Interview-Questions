# Orchestrated Restaurant Order Management System

## Task

Implement a concurrent restaurant simulation system that orchestrates interactions between customers, waiters, and chefs using thread-safe queues and proper synchronization. The system should handle the complete order lifecycle from placement to delivery while coordinating multiple actors working in parallel.

## Requirements

### Multi-threaded Actors
Create separate thread pools for customers, waiters, and chefs that run concurrently.

### Order Flow Orchestration
Manage the complete order pipeline through the following stages:

1. **Customer → Waiter** (order placement)
2. **Waiter → Chef** (order forwarding)
3. **Chef → Waiter** (completed order)
4. **Waiter → Customer** (order delivery)

### Thread-safe Communication
Use blocking queues to safely pass orders between different entity types.

### Customer-specific Delivery
Track which orders belong to which customers for proper delivery.

### Concurrent Processing
- Multiple customers can order simultaneously
- Multiple chefs can cook simultaneously

### Graceful Shutdown
Properly shut down thread pools in the correct order:

1. **Customers finish first** - Stop taking new orders
2. **Waiters finish next** - Complete pending deliveries
3. **Chefs are force-stopped last** - Terminate remaining cooking operations

### Entity Abstraction
Use enums to represent different restaurant entities:
- `CUSTOMER`
- `WAITER`
- `CHEF`

### Menu Management
Support a menu of items that customers can randomly select from.

## Implementation Details

### Key Components

- **Restaurant** - Main orchestrator managing thread pools and coordination
- **OrchestrationManager** - Handles order routing between entities using blocking queues
- **Customer** - Places orders and waits for delivery
- **Waiter** - Receives orders from customers and delivers completed orders
- **Chef** - Prepares orders received from waiters
- **Order** - Represents a customer order with menu items
- **MenuItem** - Menu item data structure

### Thread Pools

Three separate `ExecutorService` instances:
- Customer thread pool
- Waiter thread pool
- Chef thread pool

### Synchronization

Use `BlockingQueue` implementations for thread-safe order passing between different actor types.

## Level

**Mid- to Senior-level Backend Engineer**

## Key Concepts Tested

- Multi-threading and concurrency
- Thread pools and executor services
- Blocking queues and producer-consumer pattern
- Thread synchronization
- Graceful shutdown of concurrent systems
- Resource coordination
