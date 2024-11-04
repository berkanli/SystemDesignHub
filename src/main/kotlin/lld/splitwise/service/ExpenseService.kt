package lld.splitwise.service

import lld.splitwise.ExpenseType
import lld.splitwise.model.Expense
import lld.splitwise.model.User

class ExpenseService {
    private val expenses = mutableListOf<Expense>()
    private val balanceSheet = mutableMapOf<Pair<User, User>, Double>()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
        splitExpense(expense)
    }

    private fun updateBalanceSheet(from: User, to: User, amount: Double) {
        val key = Pair(from, to)
        val reverseKey = Pair(to, from)

        if (balanceSheet.containsKey(reverseKey)) {
            // Adjust if there is already debt in the reverse direction
            val existingAmount = balanceSheet[reverseKey] ?: 0.0
            if (existingAmount > amount) {
                balanceSheet[reverseKey] = existingAmount - amount
            } else {
                balanceSheet.remove(reverseKey)
                if (existingAmount < amount) {
                    balanceSheet[key] = amount - existingAmount
                }
            }
        } else {
            // Add new debt if no reverse debt exists
            balanceSheet[key] = balanceSheet.getOrDefault(key, 0.0) + amount
        }
    }

    private fun splitExpense(expense: Expense) {
        // Logic to calculate debts based on ExpenseType and update balanceSheet
        when (expense.type) {
            ExpenseType.EQUAL -> splitEqual(expense)
            ExpenseType.EXACT -> splitExact(expense)
            ExpenseType.PERCENTAGE -> splitPercentage(expense)
        }
    }

    private fun splitEqual(expense: Expense) {
        val amountPerPerson = expense.amount / expense.participants.size
        expense.participants.forEach { participant ->
            if (participant != expense.paidBy) {
                updateBalanceSheet(expense.paidBy, participant, amountPerPerson)
            }
        }
    }
    private fun splitExact(expense: Expense) {
        expense.distribution?.forEach { (participant, amount) ->
            if (participant != expense.paidBy) {
                updateBalanceSheet(expense.paidBy, participant, amount)
            }
        }
    }

    private fun splitPercentage(expense: Expense) {
        expense.distribution?.forEach { (participant, percentage) ->
            if (participant != expense.paidBy) {
                val amount = (percentage / 100) * expense.amount
                updateBalanceSheet(expense.paidBy, participant, amount)
            }
        }
    }
}