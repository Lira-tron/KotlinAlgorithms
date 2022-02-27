package concurrency.counter

class WrongVolatileCounter : Counter() {
    @Volatile
    override var counter = 0

    override suspend fun incrementCounter() {
        counter++
    }
}
