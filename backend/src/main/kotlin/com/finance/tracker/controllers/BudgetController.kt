package com.finance.tracker.controllers

import com.finance.tracker.models.Budget
import com.finance.tracker.services.BudgetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/budget")
class BudgetController(private val budgetService: BudgetService) {

    @GetMapping("/user/{userId}")
    fun getUserBudgets(@PathVariable userId: Long): ResponseEntity<List<Budget>> =
        ResponseEntity.ok(budgetService.getBudgetByUser(userId))

    @PostMapping
    fun setBudget(@RequestBody budget: Budget): ResponseEntity<Budget> =
        ResponseEntity.ok(budgetService.setBudget(budget))
}
