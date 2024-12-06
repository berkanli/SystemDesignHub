package design_pattern.adapter.adapter1

import kotlin.random.Random

class EnemyRobot{
    private val generator = Random

    fun smashWithHands(){
        val attackDamage = generator.nextInt(1, 10)
        println("Enemy Robot causes $attackDamage damage with its hands")
    }

    fun walkForward(){
        val movement = generator.nextInt(1,5)
        println("Enemy Robot walks forward $movement spaces")
    }

    fun reactToHuman(driver: String){
        println("Enemy Robot Tramps on $driver")
    }
}