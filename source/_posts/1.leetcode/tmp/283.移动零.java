/*
 * @lc app=leetcode.cn id=283 lang=java
 * 
 * [283] 移动零
 */

// @lc code=start
class Solution283 {
    // 该版本 非零数字移动了，不对哦
    // 思路：从后往前遍历，遇见0 则与最后标记置换位置
    public void moveZeroes1(int[] nums) {
        if (nums.length <= 0) {
            return;
        }
        int lastIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                swap(nums, i, lastIndex);
                lastIndex--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    /**
     * 思路：从后往前判断，如果遇到0 就将后面的数字往前移
     * 看答案更好的想法： 从前往后，收集不等于0的数，重新规划数组，剩余补零即可
     * 
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums.length <= 0) {
            return;
        }
        int lastIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                swapMulti(nums, i, lastIndex);
                lastIndex--;
            }
        }
    }

    /**
     * 交换从i 到lastIndex的所有数据
     * 
     * @param nums
     * @param i
     * @param lastIndex
     */
    private void swapMulti(int[] nums, int start, int lastIndex) {
        int temp = nums[start];
        while (start < lastIndex) {
            nums[start] = nums[start + 1];
            start++;
        }
        nums[lastIndex] = temp;
    }

    /**
     * 看答案更好的想法： 从前往后，收集不等于0的数，重新规划数组，剩余补零即可
     * 
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int insertPos = 0;
        for (int num : nums) {
            if (num != 0)
                nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
// @lc code=end
