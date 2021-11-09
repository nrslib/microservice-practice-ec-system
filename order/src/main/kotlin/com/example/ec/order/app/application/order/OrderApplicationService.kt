package com.example.ec.order.app.application.order

import com.example.ec.applicationsupportstack.applicationsupport.exceptions.NotFoundException
import com.example.ec.applicationsupportstack.kotlinsupport.AllOpen
import com.example.ec.order.api.OrderState
import com.example.ec.order.api.events.OrderCreatedEvent
import com.example.ec.order.app.domain.models.item.Item
import com.example.ec.order.app.domain.models.item.ItemId
import com.example.ec.order.app.domain.models.item.ItemRepository
import com.example.ec.order.app.domain.models.order.*
import com.example.ec.shared.money.models.money.Money
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import java.util.*

@AllOpen
class OrderApplicationService(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository
    ) {

    @Transactional
    fun createOrder(accountId: String, itemAndQuantities: List<ItemIdAndQuantity>) {
        val items = itemRepository.find(itemAndQuantities.map { ItemId(it.itemId) })
        val orderItems = makeOrderItems(itemAndQuantities, items)
        val order = Order(OrderId(UUID.randomUUID().toString()), orderItems, OrderState.APPROVAL_PENDING)
        orderRepository.save(order)

        val orderCreatedEvent = OrderCreatedEvent(
            order.id.value,
            accountId,
            order.items.items.map { com.example.ec.order.api.events.OrderItem(it.id.value, it.quantity) },
            order.total()
        )
        applicationEventPublisher.publishEvent(orderCreatedEvent)
    }

    fun approveOrder(orderId: String) {
        val order = orderRepository.find(OrderId(orderId)) ?: throw NotFoundException("not found order(orderId:${orderId})")
        order.approve()
        orderRepository.save(order)
    }

    fun rejectOrder(orderId: String) {
        val order = orderRepository.find(OrderId(orderId)) ?: throw NotFoundException("not found order(orderId:${orderId})")
        order.approve()
        orderRepository.save(order)
    }

    private fun makeOrderItems(itemAndQuantities: List<ItemIdAndQuantity>, items: List<Item>): OrderItems {
        val orderItems = itemAndQuantities.map {
            val item = items.first {item -> item.id.value == it.itemId}

            OrderItem(
                OrderItemId(it.itemId),
                it.quantity,
                item.price
            )
        }

        return OrderItems(orderItems)
    }
}