package datastructures

import io.kotest.matchers.shouldBe
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
    fun `remove success`(inputs: List<Int>, removeInputs: List<Int>, expectedValues: List<Int>) {
        val minHeap =  MinHeap<Int>()
        for(i in inputs) {
            minHeap.insert(i)
        }
        for(i in removeInputs) {
            minHeap.remove(i)
        }
        validate(minHeap, expectedValues)
    }

    private fun <E: Comparable<E>> validate(heap: MinHeap<E>, expectedValues: List<Int>) {
        heap.size shouldBe expectedValues.size
        for(expected in expectedValues) {
            heap.remove() shouldBe expected
        }
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
            Arguments.of(listOf(5,4,3,2,1), listOf(2), listOf(1,2,4,5)),
            Arguments.of(listOf(3,2,5,4,1), listOf(0,3), listOf(2,3,5)),
        )
    }
}