package system_design.caching.cache_aside

fun main() {
    val cache = InMemoryCache<Int, String>()
    val dataSource = ExampleDataSource()
    val cacheAside = CacheAside(cache, dataSource)

    // Populate the database
    dataSource.updateDataSource(1, "Cached Data")

    // Retrieve data (fetches from data source and caches it)
    println(cacheAside.get(1))  // Output: Cached Data

    // Update data and invalidate cache
    cacheAside.update(1, "Updated Data")

    // Retrieve data (fetches updated data from data source)
    println(cacheAside.get(1))  // Output: Updated Data
}