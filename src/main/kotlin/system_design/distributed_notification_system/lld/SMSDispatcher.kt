package system_design.distributed_notification_system.lld

class SMSDispatcher : NotificationDispatcher {
    override fun dispatch(notification: NotificationRequest) {
        println("Sending SMS to user ${notification.userId}: ${notification.message}")
    }
}