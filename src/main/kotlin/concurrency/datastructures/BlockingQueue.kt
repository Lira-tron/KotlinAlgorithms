package concurrency.datastructures

import kotlinx.coroutines.channels.Channel

class BlockingQueue<T>(size: Int){
    private val channel = Channel<T>(size)

    //blocks the caller if there is no size.
    suspend fun offer(item: T) {
        channel.send(item)
    }

    //blocks the caller if there are no elements
    suspend fun poll() : T {
        return channel.receive()
    }

    fun close() {
        channel.cancel()
    }
}