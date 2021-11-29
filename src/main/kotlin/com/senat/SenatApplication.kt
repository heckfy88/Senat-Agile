package com.senat.senat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class SenatApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    runApplication<SenatApplication>(*args)
}
