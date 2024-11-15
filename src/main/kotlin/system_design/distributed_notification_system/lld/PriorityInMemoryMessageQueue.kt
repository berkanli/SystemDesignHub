package system_design.distributed_notification_system.lld

import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class PriorityInMemoryMessageQueue : MessageQueue {
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    private val priorityQueue = PriorityQueue<NotificationRequest>(compareByDescending { it.priority.level })

    override fun enqueue(notification: NotificationRequest) {
        lock.withLock {
            priorityQueue.offer(notification)
            condition.signalAll() // Notify waiting consumers
            println("Notification enqueued for user ${notification.userId} with priority ${notification.priority}")
        }
    }

    override fun dequeue(): NotificationRequest? {
        lock.withLock {
            while (priorityQueue.isEmpty()) {
                condition.await() // Wait for notifications to be available
            }
            val notification = priorityQueue.poll()
            println("Notification dequeued for user ${notification.userId} with priority ${notification.priority}")
            return notification
        }
    }
}