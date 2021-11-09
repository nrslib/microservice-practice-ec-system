package com.example.ec.order.configs.debug

import com.example.ec.order.app.domain.models.item.Item
import com.example.ec.order.app.domain.models.item.ItemId
import com.example.ec.order.app.domain.models.item.ItemRepository
import com.example.ec.order.app.domain.models.order.OrderRepository
import com.example.ec.order.app.mongoinfrastructure.cosmos.item.ReactiveCosmosItemRepository
import com.example.ec.order.app.mongoinfrastructure.cosmos.order.ReactiveCosmosOrderRepository
import com.example.ec.order.app.mongoinfrastructure.persistence.item.CosmosItemRepository
import com.example.ec.order.app.mongoinfrastructure.persistence.order.CosmosOrderRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("debug")
class InfrastructureConfig {
//    @Bean
//    fun orderRepository(applicationEventPublisher: ApplicationEventPublisher): OrderRepository {
//        return InMemoryOrderRepository(applicationEventPublisher)
//    }

    @Bean
    fun orderRepository(reactiveCosmosOrderRepository: ReactiveCosmosOrderRepository): OrderRepository {
        return CosmosOrderRepository(reactiveCosmosOrderRepository)
    }

    @Bean
    fun itemRepository(reactiveCosmosItemRepository: ReactiveCosmosItemRepository): ItemRepository {
        return CosmosItemRepository(reactiveCosmosItemRepository)
    }
}
