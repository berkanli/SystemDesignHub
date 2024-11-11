package lld.caching.write_through

class WriteThroughCache<K, V>(
    private val cache: Cache<K, V>,
    private val dataSource: DataSource<K, V>
) {

    // Retrieves data, reading from cache or loading from data source if not cached
    fun get(key: K): V? {
        var value = cache.getFromCache(key)

        if (value == null) {
            value = dataSource.getFromDataSource(key)
            if (value != null) {
                cache.writeToCache(key, value)
            }
        }
        return value
    }

    // Writes data to both cache and data source to ensure consistency
    fun put(key: K, value: V) {
        cache.writeToCache(key, value)
        dataSource.writeToDataSource(key, value)
    }

}
