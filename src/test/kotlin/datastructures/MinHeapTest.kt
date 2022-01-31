package datastructures

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MinHeapTest {

    @ParameterizedTest
    @MethodSource("providesInsertValues")
    fun `insert success`(inputs: List<Int>, expectedValues: List<Int>) {
        val minHeap =  MinHeap<Int>()
        for(i in inputs) {
            minHeap.insert(i)
        }
        validate(minHeap, expectedValues)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveValues")
    fun `remove success`(inputs: List<Int>, repeatRemove: Int, expectedValues: List<Int>) {
        val minHeap =  MinHeap<Int>()
        for(i in inputs) {
            minHeap.insert(i)
        }
       repeat(repeatRemove) {
            minHeap.remove()
        }
        validate(minHeap, expectedValues)
    }

    @Test
    fun `remove throws NotSuchElementException`() {
        val minHeap =  MinHeap<Int>()
        shouldThrow<NoSuchElementException> {
            minHeap.remove()
        }
    }

    @ParameterizedTest
    @MethodSource("providesRemoveIndexValues")
    fun `remove by index success`(inputs: List<Int>, removeInputs: List<Int>, expectedValues: List<Int>) {
        val minHeap =  MinHeap<Int>()
        for(i in inputs) {
            minHeap.insert(i)
        }
        for(i in removeInputs) {
            minHeap.remove(i)
        }
        validate(minHeap, expectedValues)
    }

    @Test
    fun `remove by index throws IndexOutOfBoundsException`() {
        val minHeap =  MinHeap<Int>()
        shouldThrow<IndexOutOfBoundsException> {
            minHeap.remove(10)
        }
    }

    private fun <E: Comparable<E>> validate(heap: MinHeap<E>, expectedValues: List<Int>) {
        heap.size shouldBe expectedValues.size
        heap.elements shouldContainAll expectedValues
    }

    companion object {

        @JvmStatic
        fun providesInsertValues() = listOf(
            Arguments.of(listOf(5,4,3,2,1), listOf(1,2,3,4,5)),
            Arguments.of(listOf(3,2,5,4,1), listOf(1,2,3,4,5)),
            Arguments.of(listOf(1,2), listOf(1,2)),
        )

        @JvmStatic
        fun providesRemoveValues() = listOf(
            Arguments.of(listOf(5,4,3,2,1), 1, listOf(2,3,5,4)),
            Arguments.of(listOf(3,2,5,4,1), 2, listOf(3,4,5)),
        )

        @JvmStatic
        fun providesRemoveIndexValues() = listOf(
            Arguments.of(listOf(5,4,3,2,1), listOf(2), listOf(1,2,5,4)),
            Arguments.of(listOf(3,2,5,4,1), listOf(0,3), listOf(2,3,5)),
        )
    }
}