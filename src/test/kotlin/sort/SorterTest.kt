package sort

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import sort.Sorter.bubbleSort
import sort.Sorter.exchangeSort
import sort.Sorter.insertionSort
import sort.Sorter.mergeSort
import sort.Sorter.quickSort
import sort.Sorter.radixSort

class SorterTest {

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `bubbleSort success`(actual: IntArray, expected: IntArray) {
        actual.bubbleSort()
        actual shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `exchangeSort success`(actual: IntArray, expected: IntArray) {
        actual.exchangeSort()
        actual shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `insertionSort success`(actual: IntArray, expected: IntArray) {
        actual.insertionSort()
        actual shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `mergeSort success`(actual: IntArray, expected: IntArray) {
        actual.mergeSort()
        actual shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `quickSort success`(actual: IntArray, expected: IntArray) {
        actual.quickSort()
        actual shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `radixSort success`(actual: IntArray, expected: IntArray) {
        actual.radixSort()
        actual shouldBe expected
    }

    companion object {
        @JvmStatic
        fun providesIntArray() = listOf(
            Arguments.of(intArrayOf(3,4,5,1,2), intArrayOf(1,2,3,4,5)),
            Arguments.of(intArrayOf(5,4,3,2,1), intArrayOf(1,2,3,4,5)),
            Arguments.of(intArrayOf(2,1), intArrayOf(1,2)),
        )
    }
}