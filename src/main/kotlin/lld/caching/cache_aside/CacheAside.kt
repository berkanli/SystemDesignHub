package lld.caching.cache_aside

class CacheAside<K, V>(
    private val cache: Cache<K, V>,
    private val dataSource: DataSource<K, V>
) {

    // Retrieves data using the cache-aside pattern
    fun get(key: K): V? {
        // Try to fetch the value from cache
        var value = cache.getFromCache(key)

        // If not in cache, load from data source and cache it
        if (value == null) {
            value = dataSource.getFromDataSource(key)
            if (value != null) {
                cache.addToCache(key, value)
            }
        }
        return value
    }

    // Update data in data source and then invalidate cache
    fun update(key: K, value: V) {
        // Update the data source directly
        dataSource.updateDataSource(key, value)

        // Invalidate the cache for the updated key
        cache.invalidateCache(key)
    }
}