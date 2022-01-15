package sort

import sort.Sorter.swap

object Sorter {

    fun bubbleSort(arr: IntArray) {
        for(i in 0 until arr.size - 1) {
            for(j in 1 until arr.size) {
                if(arr[j - 1] > arr[j]) {
                    arr.swap(j - 1, j)
                }
            }
        }
    }

    fun exchangeSort(arr: IntArray) {
        for(i in 0 until arr.size - 1) {
            for(j in i + 1 until arr.size) {
                if(arr[i] > arr[j]) {
                    arr.swap(i, j)
                }
            }
        }
    }

    fun insertionSort(arr: IntArray) {
        for(i in 1 until arr.size) {
            val key = arr[i]
            var j = i - 1
            while(j >= 0 && arr[j] > key ) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = key
        }
    }

    fun mergeSort(arr: IntArray) {
        val arrTmp = IntArray(arr.size)
        mergeSort(arr, arrTmp, 0, arr.size - 1)
    }

    fun mergeSort(arr: IntArray, arrTmp: IntArray, low: Int, high: Int) {
        if(low < high) {
            val pivot = low + (high - low) / 2
            mergeSort(arr, arrTmp, low, pivot)
            mergeSort(arr, arrTmp, pivot + 1, high)
            merge(arr, arrTmp, low, pivot, high)
        }
    }

    fun merge(arr: IntArray, arrTmp: IntArray, low: Int, pivot: Int, high: Int) {
        for(i in low..high) {
            arrTmp[i] = arr[i]
        }
        var k = low
        var left = low
        var right = pivot + 1
        while(left <= pivot && right <= high) {
            arr[k++] = if( arrTmp[left] > arrTmp[right]) {
                arrTmp[right++]
            } else {
                arrTmp[left++]
            }
        }
        while(left <= pivot) {
            arr[k++] = arrTmp[left++]
        }
        while(right <= high) {
            arr[k++] = arrTmp[right++]
        }
    }

    fun quickSort(arr: IntArray) {
        quickSort(arr, 0, arr.size - 1)
    }

    fun quickSort(arr: IntArray, low: Int, high: Int) {
        if(low < high) {
            val pivot = partition(arr, low, high)
            quickSort(arr, low, pivot - 1)
            quickSort(arr, pivot + 1, high)
        }
    }

    private fun partition(arr: IntArray, low: Int, high: Int) : Int {
        var lower = low
        val pivot = arr[high]
        var next = low
        while(next < high) {
            if(arr[next] <= pivot) {
                arr.swap(lower++, next)
            }
            next++
        }
        arr.swap(lower, high)
        return lower
    }

    private fun IntArray.swap(i: Int, j: Int) {
        this[i] = this[j].also { this[j] = this[i] }
    }
}
