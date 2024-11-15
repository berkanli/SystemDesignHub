package system_design.distributed_notification_system.lld

class PushDispatcher : NotificationDispatcher {
    override fun dispatch(notification: NotificationRequest) {
        println("Sending Push Notification to user ${notification.userId}: ${notification.message}")
    }
}