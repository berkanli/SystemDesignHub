package system_design.caching.write_behind

class WriteBehindCache<K, V>(
    private val database: Database<K, V>,
    private val flushInterval: Long = 5000 // e.g., flush every 5 seconds
) : Cache<K, V> {

    private val cache: MutableMap<K, V> = mutableMapOf()
    private val writeBuffer: MutableMap<K, V> = mutableMapOf()

    init {
        startFlushService()
    }

    override fun put(key: K, value: V) {
        cache[key] = value
        writeBuffer[key] = value
    }

    override fun get(key: K): V? {
        return cache[key] ?: database.read(key)
    }

    override fun invalidate(key: K) {
        cache.remove(key)
        writeBuffer.remove(key)
        database.delete(key)
    }

    override fun flush() {
        synchronized(writeBuffer) {
            writeBuffer.forEach { (key, value) ->
                database.write(key, value)
            }
            writeBuffer.clear()
        }
    }

    private fun startFlushService() {
        val flushThread = Thread {
            while (true) {
                Thread.sleep(flushInterval)
                flush()
            }
        }
        flushThread.isDaemon = true
        flushThread.start()
    }
}
