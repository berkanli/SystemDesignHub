package design_pattern.adapter.adapter1

class EnemyRobotAdapter(private val robot: EnemyRobot): EnemyAttacker {

    override fun fireWeapon() {
        robot.smashWithHands()
    }

    override fun driveForward() {
        robot.walkForward()
    }

    override fun assignDriver(driver: String) {
        robot.reactToHuman(driver)
    }
}