package system_design.distributed_notification_system.lld

class RetryHandler(private val deadLetterQueue: MessageQueue) {

    fun retry(notification: NotificationRequest) {
        println("Retrying notification for user ${notification.userId}")
        deadLetterQueue.enqueue(notification)
    }
}