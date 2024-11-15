package system_design.caching.refresh_ahead

class ExampleDataSource : DataSource<Int, String> {
    private val database = mutableMapOf<Int, String>()  // Simulated database

    override fun getFromDataSource(key: Int): String? = database[key]

    override fun updateDataSource(key: Int, value: String) {
        database[key] = value
    }
}