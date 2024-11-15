package system_design.atm

abstract class Transaction(protected val account: Account, protected val amount: Double) {
    abstract fun execute(): Boolean
}