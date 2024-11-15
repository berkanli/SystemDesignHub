package system_design.distributed_notification_system.lld

import java.util.concurrent.LinkedBlockingQueue

class InMemoryMessageQueue : MessageQueue {
    private val queue = LinkedBlockingQueue<NotificationRequest>()

    override fun enqueue(notification: NotificationRequest) {
        queue.put(notification) // Blocks if the queue is full, ensuring backpressure
        println("Notification enqueued for user ${notification.userId}")
    }

    override fun dequeue(): NotificationRequest? {
        return queue.poll()?.also {
            println("Notification dequeued for user ${it.userId}")
        }
    }
}
