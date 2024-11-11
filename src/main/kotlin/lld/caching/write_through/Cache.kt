package lld.caching.write_through

interface Cache <K, V> {
    fun getFromCache(key: K): V?
    fun writeToCache(key: K, value: V)
    fun invalidateCache(key: K)
}
