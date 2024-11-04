package lld.splitwise.model

data class Group(
    val id: String,
    val name: String,
    val members: List<User>,
    val expenses: MutableList<Expense> = mutableListOf()
)