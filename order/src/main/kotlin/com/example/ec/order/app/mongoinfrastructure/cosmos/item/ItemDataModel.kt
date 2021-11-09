package com.example.ec.order.app.mongoinfrastructure.cosmos.item

import com.azure.spring.data.cosmos.core.mapping.Container
import com.example.ec.shared.money.models.money.Money
import org.springframework.data.annotation.Id

@Container(containerName = "order_items")
class ItemDataModel {
    @Id
    lateinit var id: String
    lateinit var price: String
    lateinit var name: String

    constructor()

    constructor(id: String, price: String, name: String) {
        this.id = id
        this.price = price
        this.name = name
    }
}