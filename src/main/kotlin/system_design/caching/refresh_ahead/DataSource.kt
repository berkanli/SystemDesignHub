package system_design.caching.refresh_ahead

interface DataSource<K, V> {
    fun getFromDataSource(key: K): V?
    fun updateDataSource(key: K, value: V)
}