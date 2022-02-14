package datastructures

class TreeNode<E: Comparable<E>>(var value: E, var left: TreeNode<E>? = null, var right: TreeNode<E>? = null)

class BST <E: Comparable<E>> {
    var root: TreeNode<E>? = null
        private set

    fun insert(e: E) {
        root = insert(root, e)
    }

    fun delete(e: E): E? {
        if(isEmpty()) {
            throw NoSuchElementException("Tree is empty")
        }
        root = delete(root, e)
        return root?.value
    }

    fun isEmpty() = root == null

    fun preOrder(action: (TreeNode<E>) -> Unit) = preOrder(root, action)

    fun inOrder(action: (TreeNode<E>) -> Unit) = inOrder(root, action)

    fun postOrder(action: (TreeNode<E>) -> Unit) = postOrder(root, action)

    fun lookUp(e: E) : Boolean = lookUp(root, e)

    private fun lookUp(node: TreeNode<E>?, e: E) : Boolean =
        when {
            node == null -> false
            node.value == e -> true
            node.value > e -> lookUp(node.left, e)
            else -> lookUp(node.right, e)
        }

    fun size() = size(root)

    fun maxDepth() = maxDepth(root)

    fun minDepth() = minDepth(root)

    private fun minDepth(node: TreeNode<E>?): Int {
        if(node == null) {
            return 0
        }
        return 1 + minOf(maxDepth(node.left), maxDepth(node.right))
    }

    private fun maxDepth(node: TreeNode<E>?): Int {
        if(node == null) {
            return 0
        }
        return 1 + maxOf(maxDepth(node.left), maxDepth(node.right))
    }

    private fun size(node: TreeNode<E>?): Int {
        if(node == null) {
            return 0
        }
        return 1 + size(node.left) + size(node.right)
    }

    private fun insert(node: TreeNode<E>?, e: E): TreeNode<E> =
        when {
            node == null -> TreeNode(e)
            node.value < e -> {
                node.right = insert(node.right, e)
                node
            }
            else -> {
                node.left = insert(node.left, e)
                node
            }
        }

    private fun preOrder(node:TreeNode<E>?, action: (TreeNode<E>) -> Unit) {
        node?.let {
            action(it)
            preOrder(it.left, action)
            preOrder(it.right, action)
        }
    }

    private fun inOrder(node:TreeNode<E>?, action: (TreeNode<E>) -> Unit) {
        node?.let {
            inOrder(it.left, action)
            action(it)
            inOrder(it.right, action)
        }
    }

    private fun postOrder(node:TreeNode<E>?, action: (TreeNode<E>) -> Unit) {
        node?.let {
            postOrder(it.left, action)
            postOrder(it.right, action)
            action(it)
        }
    }

    private fun delete(node: TreeNode<E>?, e: E): TreeNode<E>? =
        when {
            node == null -> null
            node.value < e -> {
                node.right = delete(node.right, e)
                node
            }
            node.value > e -> {
                node.left = delete(node.left, e)
                node
            }
            else -> {
                deleteEqual(node)
            }
       }

    private fun deleteEqual(node: TreeNode<E>): TreeNode<E>? = when {
        node.left == null && node.right == null -> null
        node.left == null -> node.right
        node.right == null -> node.left
        else -> {
            val min = getMin(node.right!!)
            node.value = min
            node.right = delete(node.right, min)
            node
        }

    }

    private fun getMin(node: TreeNode<E>): E {
        var node = node
        while(node.left != null) {
            node = node.left!!
        }
        return node.value
    }
}