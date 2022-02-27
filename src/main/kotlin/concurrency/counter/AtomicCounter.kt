package concurrency.counter

import java.util.concurrent.atomic.AtomicInteger

class AtomicCounter : Counter() {
    private val atomicCounter = AtomicInteger(0)

    override var counter : Int = atomicCounter.get()
        get() = atomicCounter.get()

    override suspend fun incrementCounter() {
        atomicCounter.incrementAndGet()
    }
}