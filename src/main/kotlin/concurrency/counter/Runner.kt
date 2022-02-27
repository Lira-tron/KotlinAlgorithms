package concurrency.counter

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
    val time = measureTimeMillis {
        coroutineScope {
            repeat(10000) {
                launch {
                    repeat(1000) {
                        action()
                    }
                }
            }
        }
    }
    println("Millis: $time")
}

fun main() = runBlocking {
    val counters = listOf(
        WrongVolatileCounter(),
        AtomicCounter(),
        SynchronizedCounter(),
        MutexCounter(),
        ReentrantLockCounter(),
        SemaphoreCounter(),
        SingleThreadCounter(),
        FlowCounter(),
        ChannelCounter(this),
    )

    counters.forEach {
        run(it)
    }
    coroutineContext.cancelChildren()
}

private suspend fun run(counter: Counter)  = withContext(Dispatchers.Default) {
    launch {
        print("${counter.javaClass.kotlin.simpleName} ")
        massiveRun {
            counter.incrementCounter()
        }
    }.join()
    println("${counter.javaClass.kotlin.simpleName} Counter: ${counter.value}")
}