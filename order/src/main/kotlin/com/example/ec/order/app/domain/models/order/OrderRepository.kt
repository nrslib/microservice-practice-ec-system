package com.example.ec.order.app.domain.models.order

interface OrderRepository {
    fun find(id: OrderId): Order?
    fun save(order: Order)
}