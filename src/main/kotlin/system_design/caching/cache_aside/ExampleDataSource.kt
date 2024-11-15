package system_design.caching.cache_aside

class ExampleDataSource : DataSource<Int, String> {
    private val database = mutableMapOf<Int, String>()  // Simulated database

    override fun getFromDataSource(key: Int): String? {
        return database[key]
    }

    override fun updateDataSource(key: Int, value: String) {
        database[key] = value
    }
}