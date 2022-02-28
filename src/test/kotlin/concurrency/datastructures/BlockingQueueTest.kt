package concurrency.datastructures

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class BlockingQueueTest {

    @Test
    fun `Blocking queue success`() = runBlocking {
        val queue = BlockingQueue<Int>(10)
        withContext(Dispatchers.Default) {
            for (i in 0 until 10) {
                queue.offer(i)
            }
            for(i in 0 until 10) {
                println(async { queue.poll() }.await())
            }
            queue.close()
        }
    }
}