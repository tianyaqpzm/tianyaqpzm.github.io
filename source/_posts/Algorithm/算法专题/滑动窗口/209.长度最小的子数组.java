/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 */

// @lc code=start
class Solution {
    /**
     * 暴力破解
     * 
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // i 代表子数组的首位，j = i， j++; 就是连续数组，每次更新j的位置
            // i 代表末位，j 代表 首位，j++; 每次必然以0开头
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLength = Math.min(j - i + 1, minLength);
                    break;
                }
            }
        }
        return Integer.MAX_VALUE == minLength ? 0 : minLength;
    }

    /**
     * 滑动窗口：其实就是双指针
     * 
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0; // 窗口的起始位置
        int subLength = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            // 动态调整滑动窗口的起始位置
            while (sum >= target) {
                subLength = j - i + 1;
                minLength = minLength < subLength ? minLength : subLength;
                sum -= nums[i++];
            }
        }
        return Integer.MAX_VALUE == minLength ? 0 : minLength;
    }

    // public static void main(String[] args) {
    // Solution209 solution209 = new Solution209();
    // int res = solution209.minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 });
    // System.out.println(res);
    // }
}
// @lc code=end
