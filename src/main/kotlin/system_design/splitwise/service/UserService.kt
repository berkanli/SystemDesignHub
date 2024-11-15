package system_design.splitwise.service

import system_design.splitwise.model.User

class UserService {
    private val users = mutableMapOf<String, User>()

    fun addUser(user: User) { users[user.id] = user }
    fun getUser(id: String): User? = users[id]
    fun getAllUsers(): List<User> = users.values.toList()
}