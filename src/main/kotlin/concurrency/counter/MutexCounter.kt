package concurrency.counter

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MutexCounter : Counter() {
    override var counter = 0
    private val mutex = Mutex()

    override suspend fun incrementCounter() {
        mutex.withLock {
            counter++
        }
    }
}