package concurrency.counter

abstract class Counter {
    protected abstract var counter : Int
    val value : Int
        get() = counter

    abstract suspend fun incrementCounter()
}