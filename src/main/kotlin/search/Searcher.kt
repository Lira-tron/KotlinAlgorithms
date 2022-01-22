package search

fun binarySearch(arr: IntArray, target : Int) : Boolean {
    var low = 0
    var high = arr.size - 1
    while(low <= high) {
        val pivot = low + (high - low) / 2
        if(arr[pivot] == target) {
            return true
        }
        if(arr[pivot] < target) {
            low = pivot + 1
        } else {
            high = pivot - 1
        }
    }
    return false
}