package concurrency.ratelimiters

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow

class TokenPerSecondBucketRateLimiter (maxTokens: Int, scope: CoroutineScope) {
    private val channel = Channel<Void?>(maxTokens)
    private val timer = flow {
        while(true) {
            delay(1000)
            emit(null)
        }
    }

    private val backgroundJob : Job

    init {
        backgroundJob = scope.launch {
            initialize()
        }
    }
    private suspend fun initialize() {
        timer.collect {
            channel.send(null)
        }
    }

    suspend fun getToken() {
        channel.receive()
        println("Granted token to ${Thread.currentThread()} at ${System.currentTimeMillis()/1000}")
    }

    fun shutdown() {
        channel.cancel()
        backgroundJob.cancel()
    }

}