package design_pattern.adapter.adapter1

fun main() {
    val is3 = EnemyTank()
    val robot = EnemyRobot()
    val robotAdapter = EnemyRobotAdapter(robot)

    println("The Robot")
    robot.reactToHuman("John")
    robot.walkForward()
    robot.smashWithHands()
    println("------------------")
    println("The Enemy Tank")
    is3.assignDriver("Doe")
    is3.driveForward()
    is3.fireWeapon()
    println("------------------")
    println("The Robot with Adapter")
    robotAdapter.assignDriver("Frank")
    robotAdapter.driveForward()
    robotAdapter.fireWeapon()
}