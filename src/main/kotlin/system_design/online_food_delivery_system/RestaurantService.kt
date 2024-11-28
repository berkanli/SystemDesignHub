package system_design.online_food_delivery_system

class RestaurantService(private val restaurants: List<Restaurant>) {
    fun getRestaurants(): List<Restaurant>{
        return restaurants
    }

    fun getMenu(restaurantId: Int): List<MenuItem>? {
        return restaurants.find { it.id == restaurantId }?.menu
    }
}