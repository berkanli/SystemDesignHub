package system_design.caching.write_behind

class InMemoryDatabase<K, V> : Database<K, V> {
    private val storage: MutableMap<K, V> = mutableMapOf()

    override fun write(key: K, value: V) {
        storage[key] = value
    }

    override fun read(key: K): V? {
        return storage[key]
    }

    override fun delete(key: K) {
        storage.remove(key)
    }
}
