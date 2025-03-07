package com.finance.tracker.services

import com.finance.tracker.models.Transaction
import com.finance.tracker.repositories.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {
    fun getAllTransactions(): List<Transaction> = transactionRepository.findAll()

    fun getTransactionsByUser(userId: Long): List<Transaction> =
        transactionRepository.findAll().filter { it.user.id == userId }

    fun addTransaction(transaction: Transaction): Transaction = transactionRepository.save(transaction)

    fun deleteTransaction(id: Long) = transactionRepository.deleteById(id)
}
