package com.example.ec.shared

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SharedApplication

fun main(args: Array<String>) {
    runApplication<SharedApplication>(*args)
}
