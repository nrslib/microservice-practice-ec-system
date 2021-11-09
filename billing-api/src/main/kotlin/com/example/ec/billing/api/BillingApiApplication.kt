package com.example.ec.billing.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BillingApiApplication

fun main(args: Array<String>) {
    runApplication<BillingApiApplication>(*args)
}
