package sort

fun IntArray.bubbleSort() {
    val arr = this
    for (i in 0 until arr.size - 1) {
        for (j in 1 until arr.size) {
            if (arr[j - 1] > arr[j]) {
                arr.swap(j - 1, j)
            }
        }
    }
}

fun IntArray.exchangeSort() {
    val arr = this
    for (i in 0 until arr.size - 1) {
        for (j in i + 1 until arr.size) {
            if (arr[i] > arr[j]) {
                arr.swap(i, j)
            }
        }
    }
}

fun IntArray.insertionSort() {
    val arr = this
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}

fun IntArray.mergeSort() {
    val arr = this
    val arrTmp = IntArray(arr.size)
    mergeSort(arr, arrTmp, 0, arr.size - 1)
}

private fun mergeSort(arr: IntArray, arrTmp: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pivot = low + (high - low) / 2
        mergeSort(arr, arrTmp, low, pivot)
        mergeSort(arr, arrTmp, pivot + 1, high)
        merge(arr, arrTmp, low, pivot, high)
    }
}

private fun merge(arr: IntArray, arrTmp: IntArray, low: Int, pivot: Int, high: Int) {
    for (i in low..high) {
        arrTmp[i] = arr[i]
    }
    var k = low
    var left = low
    var right = pivot + 1
    while (left <= pivot && right <= high) {
        arr[k++] = if (arrTmp[left] > arrTmp[right]) {
            arrTmp[right++]
        } else {
            arrTmp[left++]
        }
    }
    while (left <= pivot) {
        arr[k++] = arrTmp[left++]
    }
    while (right <= high) {
        arr[k++] = arrTmp[right++]
    }
}

fun IntArray.quickSort() {
    val arr = this
    quickSort(arr, 0, arr.size - 1)
}

private fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pivot = partition(arr, low, high)
        quickSort(arr, low, pivot - 1)
        quickSort(arr, pivot + 1, high)
    }
}

private fun partition(arr: IntArray, low: Int, high: Int): Int {
    var lower = low
    val pivot = arr[high]
    var next = low
    while (next < high) {
        if (arr[next] <= pivot) {
            arr.swap(lower++, next)
        }
        next++
    }
    arr.swap(lower, high)
    return lower
}

fun IntArray.radixSort() {
    val arr = this
    val max = arr.maxOrNull() ?: 0
    var exp = 1
    val bucket = Array(10) { mutableListOf<Int>() }
    while (max / exp > 0) {
        countSort(arr, bucket, exp)
        exp *= 10
    }
}

private fun countSort(arr: IntArray, bucket: Array<MutableList<Int>>, exp: Int) {
    for (i in bucket.indices) {
        bucket[i] = mutableListOf()
    }
    for (num in arr) {
        val index = (num / exp) % 10
        bucket[index] += num
    }
    var index = 0
    for (list in bucket) {
        for (num in list) {
            arr[index++] = num
        }
    }
}

private fun IntArray.swap(i: Int, j: Int) {
    this[i] = this[j].also { this[j] = this[i] }
}
