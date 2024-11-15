package system_design.caching.refresh_ahead

import kotlin.time.Duration.Companion.seconds

fun main() {
    val cache = InMemoryCache<Int, String>()
    val dataSource = ExampleDataSource()
    val refreshAheadCache = RefreshAheadCache(cache, dataSource, refreshInterval = 10.seconds)

    // Populate the database
    dataSource.updateDataSource(1, "Initial Data")

    // Start auto-refresh
    refreshAheadCache.startAutoRefresh()

    // Retrieve data (fetches from data source and writes to cache)
    println(refreshAheadCache.get(1))  // Output: Initial Data

    // Update data in data source (simulates a change in database)
    dataSource.updateDataSource(1, "Updated Data")

    // Wait for refresh interval to pass and observe the cache automatically refresh
    Thread.sleep(12000)  // Sleep to allow background refresh to complete
    println(refreshAheadCache.get(1))  // Output: Updated Data
}