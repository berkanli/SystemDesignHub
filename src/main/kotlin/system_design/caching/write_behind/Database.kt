package system_design.caching.write_behind

interface Database<K, V> {
    fun write(key: K, value: V)
    fun read(key: K): V?
    fun delete(key: K)
}