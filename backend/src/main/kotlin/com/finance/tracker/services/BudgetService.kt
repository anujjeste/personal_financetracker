package com.finance.tracker.services

import com.finance.tracker.models.Budget
import com.finance.tracker.repositories.BudgetRepository
import org.springframework.stereotype.Service

@Service
class BudgetService(private val budgetRepository: BudgetRepository) {
    fun getBudgetByUser(userId: Long): List<Budget> =
        budgetRepository.findAll().filter { it.user.id == userId }

    fun setBudget(budget: Budget): Budget = budgetRepository.save(budget)
}
