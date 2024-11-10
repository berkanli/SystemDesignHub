package lld.caching.cache_aside

// Represents a data source, typically a database
interface DataSource<K, V> {
    fun getFromDataSource(key: K): V?
    fun updateDataSource(key: K, value: V)
}