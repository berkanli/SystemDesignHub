package system_design.splitwise.model

data class Transaction(
    val id: String,
    val from: User,
    val to: User,
    val amount: Double
)