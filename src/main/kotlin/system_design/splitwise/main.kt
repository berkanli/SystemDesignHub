package system_design.splitwise

import system_design.splitwise.model.Expense
import system_design.splitwise.model.User
import system_design.splitwise.service.ExpenseService
import system_design.splitwise.service.GroupService
import system_design.splitwise.service.UserService

fun main() {
    val userService = UserService()
    val expenseService = ExpenseService()
    val groupService = GroupService()

    // Create users
    val user1 = User("1", "Alice", "alice@example.com")
    val user2 = User("2", "Bob", "bob@example.com")
    userService.addUser(user1)
    userService.addUser(user2)

    // Create a group
    val group = groupService.createGroup("g1", "Trip to Vegas", listOf(user1, user2))

    // Add an expense
    val expense = Expense(
        id = "e1",
        amount = 100.0,
        description = "Dinner",
        paidBy = user1,
        type = ExpenseType.EQUAL,
        participants = listOf(user1, user2)
    )

    expenseService.addExpense(expense)
    groupService.addExpenseToGroup(group.id, expense)
}