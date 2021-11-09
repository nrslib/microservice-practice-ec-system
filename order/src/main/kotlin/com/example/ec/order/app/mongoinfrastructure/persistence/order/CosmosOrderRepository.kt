package com.example.ec.order.app.mongoinfrastructure.persistence.order

import com.example.ec.order.app.domain.models.order.*
import com.example.ec.order.app.mongoinfrastructure.cosmos.order.OrderDataModel
import com.example.ec.order.app.mongoinfrastructure.cosmos.order.OrderItemDataModel
import com.example.ec.order.app.mongoinfrastructure.cosmos.order.ReactiveCosmosOrderRepository
import com.example.ec.shared.money.models.money.Money

class CosmosOrderRepository(private val orderMongoRepository: ReactiveCosmosOrderRepository) : OrderRepository {
    override fun find(id: OrderId): Order? {
        val target = orderMongoRepository.findById(id.value)

        return target.map(this::convert).block()
    }

    override fun save(order: Order) {
        val dataModel = convert(order)
        orderMongoRepository.save(dataModel).block()
    }

    private fun convert(dataModel: OrderDataModel): Order {
        return Order(
            OrderId(dataModel.id),
            OrderItems(dataModel.items.map { OrderItem(OrderItemId(it.id), it.quantity, Money(it.price)) }),
            dataModel.state
        )
    }

    private fun convert(order: Order): OrderDataModel {
        return OrderDataModel(
            order.id.value,
            order.state,
            order.items.items.map { OrderItemDataModel(it.id.value, it.quantity, it.price.amount.toString()) }
        )
    }
}