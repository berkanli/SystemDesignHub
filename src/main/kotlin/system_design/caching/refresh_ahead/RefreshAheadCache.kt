package system_design.caching.refresh_ahead

import kotlinx.coroutines.*

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class RefreshAheadCache<K, V>(
    private val cache: Cache<K, V>,
    private val dataSource: DataSource<K, V>,
    private val refreshInterval: Duration = 5.seconds
) {

    // Retrieves data, reading from cache or loading from data source if not cached
    fun get(key: K): V? {
        var value = cache.getFromCache(key)

        // If not in cache, load from data source and add to cache
        if (value == null) {
            value = dataSource.getFromDataSource(key)
            if (value != null) {
                cache.writeToCache(key, value, refreshInterval)
            }
        }
        return value
    }

    // Initializes a background job to refresh cache entries before expiration
    fun startAutoRefresh() {
        CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                (cache as? InMemoryCache<K, V>)?.refreshExpiringEntries(dataSource, refreshInterval)
                delay(refreshInterval.inWholeMilliseconds)
            }
        }
    }

}