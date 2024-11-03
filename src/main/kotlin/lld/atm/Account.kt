package lld.atm

abstract class Account(val accountNumber: String, var balance: Double) {
    abstract fun deposit(amount: Double): Boolean
    abstract fun withdraw(amount: Double): Boolean
}