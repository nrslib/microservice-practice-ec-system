package com.example.ec.order.web.controllers

import com.example.ec.order.app.application.order.OrderApplicationService
import com.example.ec.order.web.models.order.post.OrderPostRequest
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/order")
class OrderController(private val orderApplicationService: OrderApplicationService) {
    @GetMapping
    fun get(): String {
        return "hello";
    }

    @PostMapping
    fun post(@RequestBody body: OrderPostRequest) {
        orderApplicationService.createOrder(body.accountId, body.items)
    }
}