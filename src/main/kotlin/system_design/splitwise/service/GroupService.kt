package system_design.splitwise.service

import system_design.splitwise.model.Expense
import system_design.splitwise.model.Group
import system_design.splitwise.model.User

class GroupService {
    private val groups = mutableMapOf<String, Group>()

    fun createGroup(id: String, name: String, members: List<User>): Group {
        val group = Group(id, name, members)
        groups[id] = group
        return group
    }

    fun addExpenseToGroup(groupId: String, expense: Expense) {
        val group = groups[groupId] ?: throw IllegalArgumentException("Group not found")
        group.expenses.add(expense)
    }
}