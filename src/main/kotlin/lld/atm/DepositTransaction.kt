package lld.atm

class DepositTransaction(account: Account, amount: Double) : Transaction(account, amount) {
    override fun execute(): Boolean {
        return account.deposit(amount)
    }
}