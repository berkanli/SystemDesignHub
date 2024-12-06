package design_pattern.adapter.adapter1

import kotlin.random.Random

class EnemyTank: EnemyAttacker {
    private val generator = Random

    override fun fireWeapon() {
        val attackDamage = generator.nextInt(1,10)
        println("Enemy Tank does $attackDamage damage")
    }

    override fun driveForward() {
        val movement = generator.nextInt(1, 5)
        println("Enemy Tank moves $movement spaces")
    }

    override fun assignDriver(driver: String) {
        println("$driver is driving the tank")
    }
}