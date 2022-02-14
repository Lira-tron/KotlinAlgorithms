package datastructures

internal class TrieNode(val map : MutableMap<Char, TrieNode> = mutableMapOf(),
                       var isEndOfWord: Boolean = false)

class TrieTree {
    //Visible for testing
    internal val root = TrieNode()

    fun add(word: String) {
        var cur = root
        for(c in word) {
            if(c in cur.map) {
                cur.map[c]?.let {
                    cur = it
                }
            } else {
                cur.map[c] = TrieNode().also { cur = it }
            }
        }
        cur.isEndOfWord = true
    }

    fun search(word: String) : Boolean {
        var cur = root
        for(c in word) {
            if(c !in cur.map) {
                return false
            }
            cur.map[c]?.let {
                cur = it
            }
        }
        return cur.isEndOfWord
    }

    fun remove(word: String) : Boolean {
        val (isFound, _) = remove(word, root, 0)
        return isFound
    }

    private fun remove(word: String, node: TrieNode, index: Int) : Pair<Boolean, Boolean> {
        if(index == word.length) {
            if(!node.isEndOfWord) {
                return Pair(false, false)
            }
            node.isEndOfWord = false
            return Pair(true, node.map.isEmpty())
        }
        val c = word[index]
        if(c !in node.map) {
            return Pair(false, false)
        }
        val (isFound , shouldDelete) = remove(word, node.map[c]!!, index + 1)
        return if(shouldDelete) {
            node.map.remove(c)
            Pair(isFound, node.map.isEmpty())
        } else {
            Pair(isFound, false)
        }
    }


}