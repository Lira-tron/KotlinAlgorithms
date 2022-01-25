package datastructures

data class NodeList<T>(val value : T, var next : NodeList<T>? = null)

class LinkedList<T : Comparable<T>> {
    var head : NodeList<T>? = null
    var last : NodeList<T>? = null

    fun add(value: T) : Boolean {
        val node = NodeList(value)
        if(isEmpty()) {
            head = node
            last = node
        } else {
            last?.next = node
            last = last?.next
        }
        return true
    }

    fun remove(value: T) : Boolean {
        var curNode = head
        if(curNode?.value == value) {
            head = head?.next
            if(isEmpty()) {
                last = head
            }
            return true
        }
        while(curNode?.next != null) {
            if (curNode.next?.value == value) {
                if(last == curNode.next) {
                    last = curNode
                }
                curNode.next = curNode.next?.next
                return true
            }
            curNode = curNode.next
        }
        return false
    }

    fun reverse() : LinkedList<T> {
        if(isEmpty()) {
            return this
        }
        var node = head?.next
        head?.next = null
        last = head
        while(node != null) {
            val tmp = node.next
            node.next = head.also { head = node }
            node = tmp
        }
        return this
    }

    fun removeDuplicates() : LinkedList<T> {
        if(isEmpty()) {
            return this
        }
        var cur = head
        while(cur != null) {
            var node = cur
            while (node?.next != null) {
                if (node.next?.value == cur.value) {
                    if(last == node.next) {
                        last = node
                    }
                    node.next = node.next?.next
                } else {
                    node = node.next
                }
            }
            cur = cur.next
        }
        return this
    }

    fun removeDuplicatesHashSet() : LinkedList<T> {
        if(isEmpty()) {
            return this
        }
        val visited = mutableSetOf<T>()
        var node = head
        while(node?.next != null) {
            visited += node.value
            if(visited.contains(node.next?.value)) {
                if(last == node.next) {
                    last = node
                }
                node.next = node.next?.next
            } else {
                node = node.next
            }
        }
        return this
    }

    fun isEmpty() : Boolean = head == null

    fun isNotEmpty() = !isEmpty()

    fun asSequence() = sequence {
        var node = head
        while(node != null) {
            yield(node.value)
            node = node.next
        }
    }

}