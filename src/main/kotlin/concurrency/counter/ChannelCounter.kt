package concurrency.counter

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

private enum class Actions {
    INCREMENT,
    SHUTDOWN,
}

class ChannelCounter(scope: CoroutineScope) : Counter() {
    override var counter: Int = 0
    private val channel = Channel<Actions>()

    init {
        scope.processEvent(channel)
    }

    private fun CoroutineScope.processEvent(channel: ReceiveChannel<Actions>)  = launch(Dispatchers.Default) {
        channel.consumeEach() {
            ensureActive()
            when(it) {
                Actions.INCREMENT -> counter++
                else -> cancel()
            }

        }
    }

    override suspend fun incrementCounter() {
        channel.send(Actions.INCREMENT)
    }

    suspend fun shutdown() {
        channel.send(Actions.SHUTDOWN)
    }
}