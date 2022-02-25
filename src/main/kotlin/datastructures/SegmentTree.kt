package datastructures

class SegmentTree(nums: IntArray) {
    private val values : IntArray
    private val originalSize = nums.size

    init {
        values = IntArray(nextPowerOfTwo(nums.size) * 2 - 1)
        generate(nums, 0, nums.size - 1, 0)
    }

    private fun nextPowerOfTwo(value: Int): Int {
        val highestOneBit = Integer.highestOneBit(value)
        return if (value == highestOneBit) value else highestOneBit shl 1
    }

    private fun generate(nums: IntArray, low: Int, high: Int, indexTree: Int) {
        if(low == high) {
            values[indexTree] = nums[low]
        } else {
            val mid = low + (high - low) / 2
            generate(nums, low, mid, indexTree * 2 + 1)
            generate(nums, mid + 1, high, indexTree * 2 + 2)
            values[indexTree] = minOf(values[indexTree * 2 + 1], values[indexTree * 2 + 2])
        }
    }

    fun getMin(low: Int, high: Int) : Int {
        return getMin(low, high, 0, originalSize - 1, 0)
    }

    private fun getMin(low: Int, high: Int, indexLeft: Int, indexRight: Int, indexTree: Int) : Int {
        if(low <= indexLeft && high >= indexRight) {
            return values[indexTree]
        }
        if(low > indexRight || high < indexLeft) {
            return Int.MAX_VALUE
        }
        val mid = indexLeft + (indexRight - indexLeft) / 2

        return minOf(getMin(low, high, indexLeft, mid, indexTree * 2 + 1),
            getMin(low, high, mid + 1, high, indexTree * 2 + 2))
    }
}