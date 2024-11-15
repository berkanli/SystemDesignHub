package system_design.distributed_notification_system.lld

class NotificationConsumer(
    private val queue: MessageQueue,
    private val dispatchers: Map<ChannelType, NotificationDispatcher>
) {
    fun processNotifications() {
        while (true) {
            val notification = queue.dequeue() ?: continue
            try {
                val dispatcher = dispatchers[notification.channel]
                dispatcher?.dispatch(notification)
            } catch (e: Exception) {
                println("Failed to process notification: ${e.message}")
            }
        }
    }
}