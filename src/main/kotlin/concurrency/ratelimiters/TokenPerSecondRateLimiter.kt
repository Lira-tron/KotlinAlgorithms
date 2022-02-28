package concurrency.ratelimiters

import kotlinx.coroutines.delay


//Write a rate limiter that generates one token every second
class TokenPerSecondRateLimiter(val maxTokenSize: Long) {
    private var lastRequestedTime = System.currentTimeMillis()
    private var possibleTokens = 0L

    suspend fun getToken() {
        possibleTokens = (System.currentTimeMillis() - lastRequestedTime) / 1000
        if(possibleTokens > maxTokenSize) {
            possibleTokens = maxTokenSize
        }
        if(possibleTokens  == 0L) {
            delay(1000)
        } else {
            possibleTokens--
        }
        lastRequestedTime = System.currentTimeMillis()
        println("Granted token to ${Thread.currentThread()} at $lastRequestedTime")
    }
}