package system_design.caching.refresh_ahead

data class CacheEntry<V>(val value: V, val expirationTime: Long)