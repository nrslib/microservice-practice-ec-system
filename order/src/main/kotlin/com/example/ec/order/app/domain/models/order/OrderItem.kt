package com.example.ec.order.app.domain.models.order

import com.example.ec.shared.money.models.money.Money

class OrderItem (val id: OrderItemId, val quantity: Int, val price: Money)