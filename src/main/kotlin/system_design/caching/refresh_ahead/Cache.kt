package system_design.caching.refresh_ahead

import kotlin.time.Duration

interface Cache<K, V> {
    fun getFromCache(key: K): V?
    fun writeToCache(key: K, value: V, ttl: Duration)
    fun invalidateCache(key: K)
}
