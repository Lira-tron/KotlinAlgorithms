package concurrency.ratelimiters

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class TokenPerSecondRateLimiterTest {
    @Test
    fun `TokenPerSecondRateLimiter success`() = runBlocking {
        val rateLimiter = TokenPerSecondRateLimiter(10)
        println("Getting Token ${System.currentTimeMillis()}")
        rateLimiter.getToken()
        delay(1000)
        println("First Token ${System.currentTimeMillis()}")
        withContext(Dispatchers.Default) {
            repeat(15) {
                launch { rateLimiter.getToken() }
            }
        }

    }
}