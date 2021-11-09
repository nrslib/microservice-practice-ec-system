package com.example.ec.order.app.handler

import com.example.ec.applicationsupportstack.microservicesupport.saga.SagaManager
import com.example.ec.order.api.events.OrderCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.event.TransactionalEventListener

class OrderEventHandler(private val sagaManager: SagaManager) {
    @EventListener
    fun handle(orderCreatedEvent: OrderCreatedEvent) {
        sagaManager.start(orderCreatedEvent)
    }
}