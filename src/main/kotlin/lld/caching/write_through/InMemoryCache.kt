package lld.caching.write_through


class InMemoryCache<K, V> : Cache<K, V> {
    private val cacheMap = mutableMapOf<K, V>()

    override fun getFromCache(key: K): V? {
        println("Getting from cache")
        return cacheMap[key]
    }

    override fun writeToCache(key: K, value: V) {
        println("Writing to cache")
        cacheMap[key] = value
    }

    override fun invalidateCache(key: K) {
        cacheMap.remove(key)
    }
}
