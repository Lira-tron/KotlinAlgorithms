package concurrency.counter

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ReentrantLockCounter : Counter() {
    override var counter = 0
    private val lock = ReentrantLock()

    override suspend fun incrementCounter() {
        lock.withLock {
            counter++
        }
    }
}