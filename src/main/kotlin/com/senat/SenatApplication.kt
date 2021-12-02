package com.senat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@ComponentScans
class SenatApplication

fun main(args: Array<String>) {
    runApplication<SenatApplication>(*args)
}
