package lld.atm

object Bank {
    private val accounts = mutableMapOf<String, Account>()
    private val cards = mutableMapOf<String, Card>()

    // Authenticate using card and PIN
    fun authenticate(card: Card, pin: String): Boolean {
        return cards[card.cardNumber]?.let { it.pin == pin } == true
    }

    // Retrieve an account based on account number
    fun getAccount(accountNumber: String): Account? {
        return accounts[accountNumber]
    }

    // Add an account and its associated card to the bank's storage
    fun addAccount(account: Account, card: Card) {
        accounts[account.accountNumber] = account
        cards[card.cardNumber] = card
    }
}
