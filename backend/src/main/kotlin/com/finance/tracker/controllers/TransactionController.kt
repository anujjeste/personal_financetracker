package com.finance.tracker.controllers

import com.finance.tracker.models.Transaction
import com.finance.tracker.services.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping
    fun getAllTransactions(): ResponseEntity<List<Transaction>> =
        ResponseEntity.ok(transactionService.getAllTransactions())

    @GetMapping("/user/{userId}")
    fun getTransactionsByUser(@PathVariable userId: Long): ResponseEntity<List<Transaction>> =
        ResponseEntity.ok(transactionService.getTransactionsByUser(userId))

    @PostMapping
    fun addTransaction(@RequestBody transaction: Transaction): ResponseEntity<Transaction> =
        ResponseEntity.ok(transactionService.addTransaction(transaction))

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: Long): ResponseEntity<Void> {
        transactionService.deleteTransaction(id)
        return ResponseEntity.noContent().build()
    }
}
