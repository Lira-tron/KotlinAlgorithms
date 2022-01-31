package datastructures

private class QueueNode<E>(val value: E, var next: QueueNode<E>? = null)

class Queue<E>{
    private var head: QueueNode<E>? = null
    private var last: QueueNode<E>? = null

    fun enqueue(e: E) {
        val node = QueueNode(e)
        if(isEmpty()) {
            head = node
            last = head
        } else {
            last?.next = node
            last = node
        }
    }

    fun dequeue() : E {
        if(isEmpty()) {
            throw NoSuchElementException("Queue is empty.")
        }
        val ret = head
        head = head?.next
        if(isEmpty()) {
            last = head
        }
        return ret!!.value
    }

    fun isEmpty() = head == null

    fun isNotEmpty() = !isEmpty()
}