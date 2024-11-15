package system_design.distributed_notification_system.lld

class NotificationProducer(private val queue: MessageQueue) {
    fun sendNotification(request: NotificationRequest): Boolean {
        return try {
            queue.enqueue(request)
            true
        } catch (e: Exception){
            println("Failed to enqueue notification: ${e.message}")
            false
        }
    }
}