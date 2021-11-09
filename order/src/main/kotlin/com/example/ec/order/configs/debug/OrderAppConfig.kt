package com.example.ec.order.configs.debug

import com.example.ec.applicationsupportstack.microservicesupport.saga.SagaManager
import com.example.ec.order.app.application.order.OrderApplicationService
import com.example.ec.order.app.domain.models.item.ItemRepository
import com.example.ec.order.app.domain.models.order.OrderRepository
import com.example.ec.order.app.handler.OrderEventHandler
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("debug")
class OrderAppConfig {
    @Bean
    fun orderEventHandler(sagaManager: SagaManager): OrderEventHandler {
        return OrderEventHandler(sagaManager)
    }

    @Bean
    fun sagaManager(): SagaManager {
        return SagaManager()
    }

    @Bean
    fun orderApplicationService(applicationEventPublisher: ApplicationEventPublisher, orderRepository: OrderRepository, itemRepository: ItemRepository): OrderApplicationService {
        return OrderApplicationService(applicationEventPublisher, orderRepository, itemRepository)
    }
}