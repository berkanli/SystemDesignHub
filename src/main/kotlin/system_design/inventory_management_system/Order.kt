package system_design.inventory_management_system

data class Order(
    val id: String,
    val productId: String,
    val quantity: Int,
    var status: OrderStatus = OrderStatus.PENDING
)