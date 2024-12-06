package design_pattern.adapter

interface IBankApi{
    fun executeTransaction(transaction: TransferTransaction): Boolean
}

class XmlBankApiAdapter: IBankApi {
    private val xmlBankApi = XmlBankApi()

    override fun executeTransaction(transaction: TransferTransaction): Boolean {
        return xmlBankApi.executeTransaction(transaction)
    }
}

class JsonBankApiAdapter: IBankApi {
    private val jsonBankApi = JsonBankApi()

    override fun executeTransaction(transaction: TransferTransaction): Boolean {
        return jsonBankApi.executeTransaction(transaction)
    }
}

class JsonBankApi: IBankApi {
    override fun executeTransaction(transaction: TransferTransaction): Boolean {
        val json = """
            ""FromIBAN"": ""{{${transaction.fromIBAN}}}"",
            ""ToIBAN"": ""{{${transaction.toIBAN}}}"",
            ""Amount"": ""{{${transaction.amount}}}"",
        """.trimIndent()
        //Call bank api with JSON
        println("${JsonBankApi()} worked!")
        println(json)
        return true
    }
}

class XmlBankApi: IBankApi {
    override fun executeTransaction(transaction: TransferTransaction): Boolean {
        val xml = """
            <TransferTransaction>
                <fromIBAN>${transaction.fromIBAN}</fromIBAN>
                <toIBAN>${transaction.toIBAN}</toIBAN>
                <amount>${transaction.amount}</amount>
            </TransferTransaction>
        """.trimIndent()
        //Call bank api with XML
        println("${XmlBankApi()} worked!")
        println(xml)
        return true
    }
}

data class TransferTransaction(
    val fromIBAN: String,
    val toIBAN: String,
    val amount: Double
)

fun main() {
    val adapter = XmlBankApiAdapter()
    val transaction = TransferTransaction("cemil","baris",9.0)
    adapter.executeTransaction(transaction)
}