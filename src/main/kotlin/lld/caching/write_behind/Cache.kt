package lld.caching.write_behind

interface Cache<K, V> {
    fun put(key: K, value: V)
    fun get(key: K): V?
    fun invalidate(key: K)
    fun flush()
}
