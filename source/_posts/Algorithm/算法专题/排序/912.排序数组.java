import java.util.Random;

/*
 * @lc app=leetcode.cn id=912 lang=java
 *
 * [912] 排序数组
 */

// @lc code=start
class Solution912 {
    public int[] sortArray(int[] nums) {

        randomized_quicksort(nums, 1, nums.length - 1);
        return nums;
    }

    private void randomized_quicksort(int[] nums, int l, int r) {
        // 划分前后两部分，
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomized_quicksort(nums, l, pos);
            randomized_quicksort(nums, pos, r);
        }

    }

    private int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + 1;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution912 solution912 = new Solution912();
        solution912.sortArray(new int[] { 2, 8, 4, 1, 3, 5 });
    }
}
// @lc code=end
