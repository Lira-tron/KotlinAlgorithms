package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LinkedListTest {

    @ParameterizedTest
    @MethodSource("providesAddLinkedList")
    fun `add success`(actual: LinkedList<Int>, expected: List<Int>) {
       validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveLinkedList")
    fun `remove success`(actual: LinkedList<Int>, expected: List<Int>, target: Int) {
        actual.remove(target)
        validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesReverseLinkedList")
    fun `reverse success`(actual: LinkedList<Int>, expected: List<Int>) {
        actual.reverse()
        validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveDupsLinkedList")
    fun `remove duplicates success`(actual: LinkedList<Int>, expected: List<Int>) {
        actual.removeDuplicates()
        validate(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("providesRemoveDupsLinkedList")
    fun `remove duplicates hashset success`(actual: LinkedList<Int>, expected: List<Int>) {
        actual.removeDuplicatesHashSet()
        validate(actual, expected)
    }

    private fun validate(actual: LinkedList<Int>, expected: List<Int>) {
        var index = 0
        for(value in actual.asSequence()) {
            value shouldBe expected[index++]
        }
        actual.last?.value shouldBe
                if(expected.isEmpty()) null else expected.last()
        actual.head?.value shouldBe
                if(expected.isEmpty()) null else expected.first()
    }

    companion object {

        private fun buildList(limit: Int) : LinkedList<Int> {
            val linkedList = LinkedList<Int>()
            for(i in 1..limit) {
                linkedList.add(i)
            }
            return linkedList
        }

        @JvmStatic
        fun providesReverseLinkedList() = listOf(
            Arguments.of(buildList(3), listOf(3,2,1)),
            Arguments.of(buildList(1), listOf(1)),
        )

        @JvmStatic
        fun providesAddLinkedList() = listOf(
            Arguments.of(buildList(3), listOf(1,2,3)),
            Arguments.of(buildList(1), listOf(1)),
        )

        @JvmStatic
        fun providesRemoveLinkedList() = listOf(
            Arguments.of(buildList(3), listOf(1,3), 2),
            Arguments.of(buildList(3), listOf(2,3), 1),
            Arguments.of(buildList(3), listOf(1,2), 3),
            Arguments.of(buildList(1), listOf<Int>(), 1),
            Arguments.of(buildList(2), listOf<Int>(1,2), 4),
        )

        @JvmStatic
        fun providesRemoveDupsLinkedList() : List<Arguments> {
            val l1 = buildList(3)
            l1.add(3)
            l1.add(2)
            val l2 = buildList(3)
            l2.add(2)
            l2.add(3)
            l2.add(3)
            l2.add(3)
            val l3 = LinkedList<Int>()
            l3.add(1)
            l3.add(1)
            l3.add(1)
            l3.add(1)
            l3.add(2)
            l3.add(3)
            val l4 = LinkedList<Int>()
            l4.add(1)
            l4.add(2)
            l4.add(2)
            l4.add(1)
            l4.add(2)
            l4.add(3)

            val expected = listOf(1,2,3)
            return listOf(
                Arguments.of(l1, expected),
                Arguments.of(l2, expected),
                Arguments.of(l3, expected),
                Arguments.of(l4, expected),
                Arguments.of(buildList(3), expected),
            )
        }
    }
}