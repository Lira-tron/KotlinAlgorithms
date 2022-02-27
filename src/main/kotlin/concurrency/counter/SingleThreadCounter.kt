package concurrency.counter

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class SingleThreadCounter : Counter() {
    override var counter = 0
    private val counterContext = newSingleThreadContext("CounterContext")

    override suspend fun incrementCounter() {
        withContext(counterContext) {
            counter++
        }
    }

    fun shutdown() {
        counterContext.close()
    }
}
