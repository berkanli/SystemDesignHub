package lld.atm

class WithdrawalTransaction(account: Account, amount: Double) : Transaction(account, amount) {
    override fun execute(): Boolean {
        return account.withdraw(amount)
    }
}