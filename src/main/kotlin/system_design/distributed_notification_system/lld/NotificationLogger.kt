package system_design.distributed_notification_system.lld

class NotificationLogger {
    fun log(notification: NotificationRequest, status: String) {
        println("Notification to ${notification.userId} - Status: $status")
    }
}