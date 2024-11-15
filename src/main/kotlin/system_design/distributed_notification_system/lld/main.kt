package system_design.distributed_notification_system.lld

/*
    Execution Flow:
        Producer receives a notification request and places it in the MessageQueue.
        Consumer fetches the request from the queue.
        Consumer identifies the appropriate Dispatcher (e.g., EmailDispatcher) based on the channel type.
        Dispatcher sends the notification using a third-party API.
        On success, log the notification. On failure, retry or push to a dead-letter queue.
 */

fun main() {
    val queue = PriorityInMemoryMessageQueue()
    val emailDispatcher = EmailDispatcher()
    val smsDispatcher = SMSDispatcher()
    val pushDispatcher = PushDispatcher()

    val dispatchers = mapOf(
        ChannelType.EMAIL to emailDispatcher,
        ChannelType.SMS to smsDispatcher,
        ChannelType.PUSH to pushDispatcher
    )

    val producer = NotificationProducer(queue)
    val consumer = NotificationConsumer(queue, dispatchers)

    // Simulate notification requests
    producer.sendNotification(
        NotificationRequest("user1", "Welcome to the platform!", ChannelType.EMAIL, Priority.LOW, emptyMap())
    )

    producer.sendNotification(
        NotificationRequest("user2", "Your OTP is 1234", ChannelType.SMS, Priority.HIGH, emptyMap())
    )

    producer.sendNotification(
        NotificationRequest("user3", "You've got a new message!", ChannelType.PUSH, Priority.MEDIUM, emptyMap())
    )

    // Process notifications
    consumer.processNotifications()
}
