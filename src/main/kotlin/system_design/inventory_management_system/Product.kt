package system_design.inventory_management_system

data class Product(
    val id: String,
    val name: String,
    val sku: String,
    val price: Double,
    var quantityInStock: Int
) {
    fun updateStock(quantity: Int) {
        quantityInStock += quantity
    }
}
