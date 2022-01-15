package sort

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SorterTest {
    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `bubbleSort success`(arr: IntArray, expected: IntArray) {
        Sorter.bubbleSort(arr)
        arr shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `exchangeSort success`(arr: IntArray, expected: IntArray) {
        Sorter.exchangeSort(arr)
        arr shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `insertionSort success`(arr: IntArray, expected: IntArray) {
        Sorter.insertionSort(arr)
        arr shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `mergeSort success`(arr: IntArray, expected: IntArray) {
        Sorter.mergeSort(arr)
        arr shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `quickSort success`(arr: IntArray, expected: IntArray) {
        Sorter.quickSort(arr)
        arr shouldBe expected
    }

    @ParameterizedTest
    @MethodSource("providesIntArray")
    fun `radixSort success`(arr: IntArray, expected: IntArray) {
        Sorter.radixSort(arr)
        arr shouldBe expected
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