package lld

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex()
var counter = 0

/*
    In this example, we have a shared counter variable that is incremented by multiple coroutines. The Mutex is used
    to ensure that only one coroutine can increment the counter at a time. The withLock function is used to acquire
    the lock before incrementing the counter, and the lock is automatically released when the block of code inside
    withLock is executed.

    When you run this code, you should see that the final value of the counter is 1,000,000, which is the expected
    result since each of the 1,000 coroutines increments the counter 1,000 times. If the Mutex was not used,
    the final value of the counter could be less than 1,000,000 due to race conditions.
 */

suspend fun incrementCounter() {
    mutex.withLock {
        counter++
    }
}

/*
    By using the launch function to start multiple coroutines, we can simulate multiple threads accessing and modifying
    the shared counter variable concurrently. Then, by using a Mutex to synchronize access to the counter variable,
    we can ensure that the final value of the counter is correct, even when multiple threads are accessing and modifying
    it simultaneously.
 */

fun main() = runBlocking {
    val jobs = List(1000) {
        launch {
            repeat(1000) {
                incrementCounter()
            }
        }
    }
    jobs.forEach { it.join() }
    println("Counter = $counter")
}
