package datastructures

class DisjointSetNode(val name: String, var rank: Int = 0, var parent : DisjointSetNode? = null)

class DisjointSet {
    //Visible for testing
    internal val map = mutableMapOf<String, DisjointSetNode>()

    var count = 0

    fun makeSet(name: String) {
        if(!map.contains(name)) {
            val node = DisjointSetNode(name)
            node.parent = node
            map[name] = node
            count++
        }
    }

    fun union(first: String, second: String) {
        val node = map[first]
        val node2 = map[second]
        if(node == null || node2 == null) {
            return
        }
        val parent1 = getParent(node)
        val parent2 = getParent(node2)
        if(parent1.rank == parent2.rank) {
            parent1.rank++
            parent2.parent = parent1
            parent2.rank = 0
        } else if(parent1.rank > parent2.rank) {
            parent2.parent = parent1
            parent2.rank = 0
        } else {
            parent1.parent = parent2
            parent1.rank = 0
        }
        count--
    }

    private fun getParent(node: DisjointSetNode) : DisjointSetNode {
        if(node.parent == node) {
            return node
        }
        node.parent = getParent(node.parent!!)
        return node.parent!!
    }

}