package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DisjointSetTest {

    @Test
    fun `makeSet success`() {
        val disjointSet = DisjointSet()
        disjointSet.makeSet("a")
        disjointSet.makeSet("b")
        disjointSet.map.keys shouldBe listOf("a", "b")
        disjointSet.count shouldBe 2
    }

    @Test
    fun `unionSet success`() {
        val disjointSet = DisjointSet()
        disjointSet.makeSet("a")
        disjointSet.makeSet("b")
        disjointSet.makeSet("c")
        disjointSet.union("a", "b")

        disjointSet.map["b"]?.parent shouldBe disjointSet.map["a"]
        disjointSet.map["b"]?.rank shouldBe 0
        disjointSet.map["a"]?.rank shouldBe 1
        disjointSet.map["c"]?.parent shouldBe disjointSet.map["c"]
        disjointSet.count shouldBe 2
    }


}