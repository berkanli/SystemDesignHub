package system_design.caching.write_behind

fun main() {
    val database = InMemoryDatabase<Int, String>()
    val writeBehindCache = WriteBehindCache(database, flushInterval = 5000)

    // Put data in cache (it will be written to the database asynchronously)
    writeBehindCache.put(1, "Value1")
    writeBehindCache.put(2, "Value2")

    // Retrieve from cache
    println("Get from cache: ${writeBehindCache.get(1)}") // Outputs: Value1

    // Simulate time delay to allow flush to happen
    Thread.sleep(6000)

    // Data should now be in the database
    println("Get from database: ${database.read(1)}") // Outputs: Value1
}
