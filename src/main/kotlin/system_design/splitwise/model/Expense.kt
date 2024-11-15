package system_design.splitwise.model

import system_design.splitwise.ExpenseType

data class Expense(
    val id: String,
    val amount: Double,
    val description: String,
    val paidBy: User,
    val type: ExpenseType,
    val participants: List<User>,
    val distribution: Map<User, Double>? = null // For exact and percentage
)
