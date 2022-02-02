package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GraphTest {

    @Test
    fun `Breadth First Search success`() {
        val graph = buildGraph()
        val actualList = mutableListOf<String>()
        graph.computeBreadthFirstSearch("a") {
            actualList += it.name
        }
        val expected = listOf("a", "b", "c", "d", "e", "f", "g", "h")

        actualList shouldBe expected
    }

    @Test
    fun `Depth First Search success`() {

        val actualList = mutableListOf<String>()
        val graph = buildGraph()
        graph.computeDepthFirstSearch("a") {
            actualList += it.name
        }
        val expected = listOf("a", "b", "d", "e", "h", "c", "f", "g")

        actualList shouldBe expected
    }

    private fun buildGraph() : Graph {
        val graph = Graph()
        graph.addUndirectedEdge("a", "b")
        graph.addUndirectedEdge("a", "c")
        graph.addUndirectedEdge("b", "d")
        graph.addUndirectedEdge("b", "e")
        graph.addUndirectedEdge("e", "h")
        graph.addUndirectedEdge("c", "f")
        graph.addUndirectedEdge("c", "g")
        return graph
    }
}