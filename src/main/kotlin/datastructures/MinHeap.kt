package datastructures

class MinHeap<E: Comparable<E>>{
    //Visible for testing
    internal val elements = mutableListOf<E>()

    val size : Int
        get() = elements.size

    fun insert(e: E) {
        elements.add(e)
        bubbleUp(size - 1)
    }

    fun remove() : E {
        if(isEmpty()) {
            throw NoSuchElementException("Heap is empty")
        }
        elements.swap(0, size - 1)
        val ret = elements.removeLast()
        bubbleDown(0)
        return ret
    }

    fun remove(index: Int) : E {
        if(isNotValidIndex(index)) {
            throw IndexOutOfBoundsException()
        }
        elements.swap(index, size - 1)
        val ret = elements.removeLast()
        bubbleUp(index)
        bubbleDown(index)
        return ret
    }

    fun isEmpty() = elements.isEmpty()

    private fun bubbleUp(index: Int) {
        if(isNotValidIndex(index)) {
            return
        }
        val parentIndex = getParentIndex(index)
        if(isValidIndex(parentIndex) && elements[parentIndex] > elements[index]) {
            elements.swap(parentIndex, index)
            bubbleUp(parentIndex)
        }
    }

    private fun bubbleDown(index: Int) {
        if(isNotValidIndex(index)) {
            return
        }
        val leftIndex = getLeftChildIndex(index)
        val rightIndex = getRightChildIndex(index)
        if(isValidIndex(leftIndex) && elements[leftIndex] < elements[index]) {
            elements.swap(leftIndex, index)
            bubbleDown(leftIndex)
        } else if(isValidIndex(rightIndex) && elements[rightIndex] < elements[index]) {
            elements.swap(rightIndex, index)
            bubbleDown(rightIndex)
        }
    }

    private fun getLeftChildIndex(index: Int) = index * 2

    private fun getRightChildIndex(index: Int) = index * 2 + 1

    private fun getParentIndex(index: Int) =
        if(isNotValidIndex(index) || index == 0) -1 else index / 2

    private fun isValidIndex(index: Int) = index in 0 until size

    private fun isNotValidIndex(index: Int) = !isValidIndex(index)

    private fun MutableList<E>.swap(i: Int, j: Int) {
        this[i] = this[j].also { this[j] = this[i] }
    }

}