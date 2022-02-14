package datastructures

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BSTTest{
    @Test
    fun `PreOrder success`() {
        val tree = buildTree()
        val actualList = mutableListOf<Int>()
        val expectedList = listOf(6,4,2,5,9)

        tree.preOrder { actualList+=it.value }

        actualList shouldBe expectedList
    }

    @Test
    fun `Remove success`() {
        val tree = buildTree()
        val expectedList = listOf(9,4,2,5)
        val actualList = mutableListOf<Int>()

        tree.delete(6)

        tree.preOrder { actualList += it.value }

        actualList shouldBe expectedList
    }

    @Test
    fun `LookUp success`() {
        val tree = buildTree()
        tree.lookUp(4).shouldBeTrue()
    }

    @Test
    fun `LookUp not found`() {
        val tree = buildTree()
        tree.lookUp(11).shouldBeFalse()
    }

    @Test
    fun `size success`() {
        val tree = buildTree()
        tree.size() shouldBe 5
    }

    @Test
    fun `MaxDepth success`() {
        val tree = buildTree()
        tree.maxDepth() shouldBe 3
    }

    @Test
    fun `MinDepth success`() {
        val tree = buildTree()
        tree.minDepth() shouldBe 2
    }

    private fun buildTree() : BST<Int> {
        val tree = BST<Int>()
        tree.insert(6)
        tree.insert(4)
        tree.insert(5)
        tree.insert(2)
        tree.insert(9)

        return tree
    }

}
