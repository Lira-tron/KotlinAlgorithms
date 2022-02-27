package concurrency.counter

class SynchronizedCounter : Counter() {
    override var counter = 0

    @Synchronized override suspend fun incrementCounter() {
        counter++
    }
}