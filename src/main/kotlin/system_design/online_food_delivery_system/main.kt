package system_design.online_food_delivery_system

fun main() {
    val user = User(id = 1, name = "Alice", address = "123 Elm Street")

    val restaurant1 = Restaurant(
        id = 1,
        name = "Pizza Palace",
        menu = listOf(
            MenuItem(id = 1, name = "Margherita Pizza", price = 8.99),
            MenuItem(id = 2, name = "Pepperoni Pizza", price = 9.99)
        )
    )
    val restaurant2 = Restaurant(
        id = 2,
        name = "Burger Shack",
        menu = listOf(
            MenuItem(id = 3, name = "Cheeseburger", price = 6.99),
            MenuItem(id = 4, name = "Fries", price = 2.99)
        )
    )

    val restaurantService = RestaurantService(listOf(restaurant1, restaurant2))
    val orderService = OrderService()

    println("Available Restaurants:")
    restaurantService.getRestaurants().forEach {
        println("${it.id}: ${it.name}")
    }

    val selectedRestaurant = restaurant1
    println("\nMenu for ${selectedRestaurant.name}:")
    restaurantService.getMenu(selectedRestaurant.id)?.forEach {
        println("${it.id}: ${it.name} - \$${it.price}")
    }

    val selectedItems = listOf(restaurant1.menu[0], restaurant1.menu[1]) // Selecting two items
    val order = orderService.placeOrder(user, selectedRestaurant, selectedItems)

    println("\nOrder placed:")
    println(order)

    // Updating order status
    orderService.updateOrderStatus(order.id, OrderStatus.PREPARING)
    println("\nUpdated Order:")
    println(order.copy(status = OrderStatus.PREPARING))
}