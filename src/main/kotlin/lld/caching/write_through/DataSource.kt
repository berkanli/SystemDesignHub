package lld.caching.write_through

interface DataSource<K,V> {
    fun getFromDataSource(key: K): V?
    fun writeToDataSource(key: K, value: V)
}