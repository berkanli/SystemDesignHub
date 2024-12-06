package design_pattern.adapter.adapter1

interface EnemyAttacker {
    fun fireWeapon()
    fun driveForward()
    fun assignDriver(driver: String)
}