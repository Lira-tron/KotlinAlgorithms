package algorithms

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AlgorithmsTest {

    @Test
    fun `apply permutation succeed`() {
        val perm = mutableListOf(3,2,0,1)
        val actual = mutableListOf(1,2,3,4)
        val expected = listOf(3,4,2,1)

        applyPermutation(perm, actual)

        actual shouldBe expected
    }
}