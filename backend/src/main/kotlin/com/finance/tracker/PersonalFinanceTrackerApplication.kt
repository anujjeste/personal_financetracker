package com.finance.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonalFinanceTrackerApplication

fun main(args: Array<String>) {
    runApplication<PersonalFinanceTrackerApplication>(*args)
}

