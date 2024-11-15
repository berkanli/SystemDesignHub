package system_design.rate_limiter

import java.util.*

class UserRequestTracker(val config: LimiterConfiguration) {
    private val requestTimestamps: Queue<Long> = LinkedList()

    fun allowRequest(): Boolean {
        val currentTime = System.currentTimeMillis()

        // Remove timestamps outside the window
        while (requestTimestamps.isNotEmpty() && currentTime - requestTimestamps.peek() > config.windowSizeMs) {
            requestTimestamps.poll()
        }

        // Check if the number of requests is within the allowed limit
        return if (requestTimestamps.size < config.maxRequestsPerWindow) {
            requestTimestamps.offer(currentTime)
            true
        } else {
            false
        }
    }
}