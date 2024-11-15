package system_design.atm

class SavingsAccount(accountNumber: String, balance: Double): Account(accountNumber, balance) {
    override fun deposit(amount: Double): Boolean {
        balance += amount
        return true
    }

    override fun withdraw(amount: Double): Boolean {
        if (balance >= amount){
            balance -= amount
            return true
        }
        return false
    }
}