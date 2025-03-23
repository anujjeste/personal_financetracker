package com.finance.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    basePackages = [
        "com.finance.tracker",
        "com.finance.tracker.controllers",
        "com.finance.tracker.services",
        "com.finance.tracker.repositories",
        "com.finance.tracker.security"
    ]
)
class PersonalFinanceTrackerApplication

fun main(args: Array<String>) {
    runApplication<PersonalFinanceTrackerApplication>(*args)
}
