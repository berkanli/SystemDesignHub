package system_design.online_food_delivery_system

data class Restaurant(
    val id: Int,
    val name: String,
    val menu: List<MenuItem>
)