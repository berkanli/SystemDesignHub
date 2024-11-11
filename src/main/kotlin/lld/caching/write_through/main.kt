package lld.caching.write_through

fun main() {
    val cache = InMemoryCache<Int, String>()
    val dataSource = ExampleDataSource()
    val writeThroughCache = WriteThroughCache(cache, dataSource)

    // Populate the data source
    dataSource.writeToDataSource(1, "Initial Data")

    // Retrieve data (fetches from data source and writes to cache)
    println(writeThroughCache.get(1))  // Output: Initial Data

    // Write new data (writes to both cache and data source)
    writeThroughCache.put(1, "Updated Data")

    // Retrieve data again to verify consistency
    println(writeThroughCache.get(1))  // Output: Updated Data
}