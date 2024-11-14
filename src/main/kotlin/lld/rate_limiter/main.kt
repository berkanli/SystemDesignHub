package lld.rate_limiter

fun main() {
    val config = LimiterConfiguration(maxRequestsPerWindow = 5, windowSizeMs = 60000) // 5 requests per minute
    val rateLimiter = RateLimiter(config)

    val userId = "user1"
    rateLimiter.addUser(userId)

    repeat(7) {
        val result = rateLimiter.request(userId)
        println("Request allowed: $result")
    }
}