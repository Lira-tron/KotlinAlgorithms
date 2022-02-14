package algorithms

fun applyPermutation(perm: MutableList<Int>, values: MutableList<Int>) {
    for(i in 0 until perm.size) {
        var next = i
        while (perm[next] >= 0) {
            values[i] = values[perm[next]].also { values[perm[next]] = values[i] }
            val tmp = perm[next]
            perm[next] = perm[next] - perm.size
            next = tmp
        }
    }
}