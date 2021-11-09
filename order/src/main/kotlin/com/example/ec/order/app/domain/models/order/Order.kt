package com.example.ec.order.app.domain.models.order

import com.example.ec.applicationsupportstack.domainsupport.aggregate.AggregateRoot
import com.example.ec.order.api.OrderState

class Order(val id: OrderId, val items: OrderItems, state: OrderState) : AggregateRoot<Order>() {
    var state = state
        private set

    fun total() = items.total()

    fun approve() {
        this.state = OrderState.APPROVED
    }

    fun reject() {
        this.state = OrderState.APPROVAL_REJECTED
    }
}