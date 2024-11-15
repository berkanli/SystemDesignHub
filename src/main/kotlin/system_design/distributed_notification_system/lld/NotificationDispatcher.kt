package system_design.distributed_notification_system.lld

interface NotificationDispatcher {
    fun dispatch(notification: NotificationRequest)
}