package system_design.distributed_notification_system.lld

interface MessageQueue {
    fun enqueue(notification: NotificationRequest)
    fun dequeue(): NotificationRequest?
}