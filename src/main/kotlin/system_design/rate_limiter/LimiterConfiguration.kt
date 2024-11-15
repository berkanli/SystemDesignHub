package system_design.rate_limiter

data class LimiterConfiguration(val maxRequestsPerWindow: Int, val windowSizeMs: Long)