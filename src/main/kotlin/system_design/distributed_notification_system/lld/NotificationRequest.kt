package system_design.distributed_notification_system.lld

data class NotificationRequest(
    val userId: String,
    val message: String,
    val channel: ChannelType,
    val priority: Priority,
    val metadata: Map<String, String>
)