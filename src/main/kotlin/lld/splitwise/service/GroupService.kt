package lld.splitwise.service

import lld.splitwise.model.Expense
import lld.splitwise.model.Group
import lld.splitwise.model.User

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