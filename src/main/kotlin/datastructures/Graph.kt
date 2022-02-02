package datastructures

data class Vertex(val name: String)

data class Edge(val source: Vertex, val destination: Vertex, val weight: Int)

class Graph {
    private val vertexMap = mutableMapOf<String, Vertex>()
    private val graph = mutableMapOf<Vertex, MutableList<Edge>>()

    fun addUndirectedEdge(source: String, destination: String, weight: Int = 0) {
        val vertex = vertexMap.getOrPut(source) { Vertex(source) }
        val vertexDestination = vertexMap.getOrPut(destination) { Vertex(destination) }
        add(vertex, vertexDestination, weight)
        add(vertexDestination, vertex, weight)
    }

    fun addDirectedEdge(source: String, destination: String, weight: Int = 0) {
        val vertex = vertexMap.getOrPut(source) { Vertex(source) }
        val vertexDestination = vertexMap.getOrPut(destination) { Vertex(destination) }
        add(vertex, vertexDestination, weight)
    }

    private fun add(source: Vertex, destination: Vertex, weight: Int) {
        val edges = graph.getOrPut(source) { mutableListOf() }
        edges += Edge(source, destination, weight)
    }

    fun getAdjancyList(v: Vertex) = graph[v]

    fun computeBreadthFirstSearch(v: String, action : (v: Vertex) -> Unit) {
        val root = vertexMap[v] ?: throw NoSuchElementException()
        val deque = ArrayDeque<Vertex>()
        val visited = mutableSetOf(root)
        deque.addFirst(root)
        while(deque.isNotEmpty()) {
            val vertex = deque.removeLast()
            action(vertex)
            for(neighbor in graph[vertex]!!) {
                if(neighbor.destination !in visited) {
                    deque.addFirst(neighbor.destination)
                    visited += neighbor.destination
                }
            }
        }
    }

    fun computeDepthFirstSearch(v: String, action : (v: Vertex) -> Unit) {
        val root = vertexMap[v] ?: throw NoSuchElementException()
        computeDepthFirstSearch(root, mutableSetOf(), action)
    }

    private fun computeDepthFirstSearch(vertex: Vertex?, visited: MutableSet<Vertex>, action : (v: Vertex) -> Unit) {
        vertex?.let {
            action(vertex)
            visited += vertex
            for(neighbor in graph[vertex]!!) {
                if(neighbor.destination !in visited) {
                    computeDepthFirstSearch(neighbor.destination, visited, action)
                }
            }
        }
    }

}