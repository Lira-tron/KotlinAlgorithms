package datastructures

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test

class TrieTreeTest {

    @Test
    fun `add success`() {
        val trieTree = TrieTree()
        val word = "test"
        trieTree.add(word)
        trieTree.search(word).shouldBeTrue()
    }

    @Test
    fun `search not found success`() {
        val trieTree = TrieTree()
        val word = "test"
        trieTree.add(word + "1")
        trieTree.search(word).shouldBeFalse()
    }

    @Test
    fun `remove success`() {
        val trieTree = TrieTree()
        val word = "test"
        trieTree.remove(word).shouldBeFalse()
        trieTree.root.map.isEmpty().shouldBeTrue()
    }

    @Test
    fun `remove with Two words success`() {
        val trieTree = TrieTree()
        val word = "test"
        val word2 = "test2"
        trieTree.add(word)
        trieTree.add(word2)
        trieTree.remove(word).shouldBeTrue()
        trieTree.search(word).shouldBeFalse()
        trieTree.search(word2).shouldBeTrue()
    }



}