package lld.caching.refresh_ahead

import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration

class InMemoryCache<K, V> : Cache<K, V> {
    private val cacheMap = ConcurrentHashMap<K, CacheEntry<V>>()

    override fun getFromCache(key: K): V? {
        val entry = cacheMap[key]
        // Return value if it's not expired
        return if (entry != null && System.currentTimeMillis() < entry.expirationTime) {
            entry.value
        } else {
            cacheMap.remove(key)
            null
        }
    }

    override fun writeToCache(key: K, value: V, ttl: Duration) {
        val expirationTime = System.currentTimeMillis() + ttl.inWholeMilliseconds
        cacheMap[key] = CacheEntry(value, expirationTime)
    }

    override fun invalidateCache(key: K) {
        cacheMap.remove(key)
    }

    // Refresh entries that are about to expire by fetching from the data source
    fun refreshExpiringEntries(dataSource: DataSource<K, V>, ttl: Duration) {
        val currentTime = System.currentTimeMillis()
        for ((key, entry) in cacheMap) {
            // Check if entry is about to expire
            if (currentTime + ttl.inWholeMilliseconds / 2 >= entry.expirationTime) {
                dataSource.getFromDataSource(key)?.let { newValue ->
                    writeToCache(key, newValue, ttl)
                }
            }
        }
    }
}