package system_design.caching.write_through

class ExampleDataSource : DataSource<Int, String> {
    private val database = mutableMapOf<Int, String>()  // Simulated database

    override fun getFromDataSource(key: Int): String? = database[key]

    override fun writeToDataSource(key: Int, value: String) {
        database[key] = value
    }
}
