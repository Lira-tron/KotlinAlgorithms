package datastructures

private data class StackNode<E>(val value: E, var next: StackNode<E>? = null)

class Stack<E>{
    private var head: StackNode<E>? = null

    fun push(e: E) {
        val node = StackNode(e)
        node.next = head
        head = node
    }

    fun pop() : E {
        if(isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
        val ret = head
        head = head?.next
        return ret!!.value
    }

    fun isEmpty() = head == null
}