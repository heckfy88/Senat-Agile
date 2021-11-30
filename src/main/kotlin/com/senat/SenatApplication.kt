package com.senat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SenatApplication

fun main(args: Array<String>) {
    runApplication<SenatApplication>(*args)
}
