package system_design.distributed_notification_system.lld

enum class Priority(val level: Int) {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
}