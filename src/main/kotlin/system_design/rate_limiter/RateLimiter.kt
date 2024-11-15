package system_design.rate_limiter

class RateLimiter(val config: LimiterConfiguration) {
    private val userTrackers: MutableMap<String, UserRequestTracker> = mutableMapOf()

    fun addUser(userId: String) {
        userTrackers[userId] = UserRequestTracker(config)
    }

    fun request(userId: String): Boolean {
        val tracker = userTrackers[userId] ?: throw IllegalArgumentException("User $userId not found")

        return tracker.allowRequest()
    }
}
