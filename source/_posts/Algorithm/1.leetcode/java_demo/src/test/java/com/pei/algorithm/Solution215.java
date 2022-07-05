import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

class Test {

    public static void main(String[] args) {
        new Solution215().findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2);
    }
}

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 */

// @lc code=start
public class Solution215 {
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(nums[i]);
        }
        for (int i = 0; i < k - 1; i++) {
            priorityQueue.poll();
        }
        return priorityQueue.element();
    }

    public int findKthLargest(int[] nums, int k) {
        return randomPartition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int randomPartition(int[] nums, int low, int high, int index) {
        int randomk = low + new Random().nextInt(high - low + 1);
        // 调整随机值放置末尾
        swap(nums, randomk, high);
        // 快排划分
        int partitionIndex = partition(nums, low, high);
        if (partitionIndex == index) {
            return nums[partitionIndex];
        } else {
            return partitionIndex < index ? randomPartition(nums, partitionIndex + 1, high, index)
                    : randomPartition(nums, low, partitionIndex - 1, index);
        }
    }

    /**
     * 调整 找到第一个小于high索引道位置,
     * 以末尾为界，前面是小于 privot, 后面是大于privot
     * 3, 2, 1, 5, 6, 4
     * 4 2 1 5 6 3
     * 
     * 2 1 4 5 6 3
     * 交换privot
     * 2 1 3 5 6 4
     * 
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int partition(int[] nums, int low, int high) {
        int privot = nums[high];
        int i = low - 1;
        for (int m = low; m < high; m++) {
            if (nums[m] < privot) {
                swap(nums, m, ++i);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    /** Method2: Heap Sort; Time: O(NlogN);; Space:O(logN) **/
    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        int numsLen = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = numsLen - 1; i >= numsLen - k + 1; i--) {
            swap(nums, 0, i);
            maxHeapify(nums, 0, --heapSize);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    /**
     * 大顶堆： 取较大的，取后交换位置，递归下一层
     * 
     * @param nums
     * @param i
     * @param heapSize
     */
    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1, right = i * 2 + 2, largest = i;
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (i != largest) {
            swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }

}

// @lc code=end
