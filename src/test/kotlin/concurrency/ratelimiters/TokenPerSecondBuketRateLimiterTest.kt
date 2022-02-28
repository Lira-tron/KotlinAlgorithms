package concurrency.ratelimiters

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class TokenPerSecondBucketRateLimiterTest {
    @Test
    fun `TokenPerSecondBuketRateLimiter success`() = runBlocking {
        withContext(Dispatchers.Default) {
            val rateLimiter = TokenPerSecondBucketRateLimiter( 10, this)
            coroutineScope {
                repeat(10) {
                    launch { rateLimiter.getToken() }
                }
            }
            delay(17000)
            coroutineScope {
                repeat(15) {
                    launch { rateLimiter.getToken() }
                }
            }
            rateLimiter.shutdown()
        }
    }
}