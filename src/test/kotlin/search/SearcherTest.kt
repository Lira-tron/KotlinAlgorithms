package search

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test

class SearcherTest {

    @Test
    fun `binarySearch success`() {
        val arr = intArrayOf(1,2,3,4,5,6,7)
        val actual = binarySearch(arr, 6)
        actual.shouldBeTrue()
    }

    @Test
    fun `binarySearch target not found`() {
        val arr = intArrayOf(1,2,3,4,5,6,7)
        val actual = binarySearch(arr, 10)
        actual.shouldBeFalse()
    }
}