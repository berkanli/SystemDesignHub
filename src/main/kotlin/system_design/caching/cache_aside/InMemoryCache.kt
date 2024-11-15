package system_design.caching.cache_aside

class InMemoryCache<K, V> : Cache<K, V> {
    private val cacheMap = mutableMapOf<K, V>()

    override fun getFromCache(key: K): V? {
        return cacheMap[key]
    }

    override fun addToCache(key: K, value: V) {
        cacheMap[key] = value
    }

    override fun invalidateCache(key: K) {
        cacheMap.remove(key)
    }
}