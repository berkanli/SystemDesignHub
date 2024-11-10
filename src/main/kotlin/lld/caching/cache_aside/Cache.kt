package lld.caching.cache_aside

interface Cache<K, V> {
    fun getFromCache(key: K): V?
    fun addToCache(key: K, value: V)
    fun invalidateCache(key: K)
}