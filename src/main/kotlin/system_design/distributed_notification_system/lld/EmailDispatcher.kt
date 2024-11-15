package system_design.distributed_notification_system.lld

class EmailDispatcher : NotificationDispatcher {
    override fun dispatch(notification: NotificationRequest) {
        println("Sending email to user ${notification.userId}: ${notification.message}")
    }
}