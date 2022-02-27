package concurrency.counter

import java.util.concurrent.Semaphore

class SemaphoreCounter : Counter() {
    override var counter = 0
    private val semaphore = Semaphore(1)

    override suspend fun incrementCounter() {
        try {
            semaphore.acquire()
            counter++
        } finally {
            semaphore.release()
        }
    }
}