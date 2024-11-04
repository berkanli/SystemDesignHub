package lld.splitwise.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String? = null
)