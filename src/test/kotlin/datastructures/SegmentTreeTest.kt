package datastructures

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SegmentTreeTest {

    @Test
    fun `getMin success`() {
        val nums = intArrayOf(4,2,3,7,9)
        val segmentTree = SegmentTree(nums)
        segmentTree.getMin(0,4) shouldBe 2
        segmentTree.getMin(2, 4) shouldBe 3
    }
}