/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution11 {
    /**
     * 滑动窗口，遍历一次，调整窗口位置
     * 该方法 时间复杂度O(n^2)
     * 未能找到调整窗口的方法， 一直从面积结果出发 想找调整因素，
     * 但存在两个变量， 而最佳选择：
     * 应该是只要比较 左右两边的高度，谁高就移动另一个
     * 滑动窗口： 也不一定是从左到右，可能是从外到内～～～
     * 
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int maxValue = 0;
        // int left = 0;
        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                // 当前窗口的大小
                int temp = (i - j) * Math.min(height[i], height[j]);
                if (temp > maxValue) {
                    maxValue = temp;
                    // left = j;
                }
            }
        }
        return maxValue;
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxValue = 0;
        while (left < right) {
            int temp = (right - left) * Math.min(height[left], height[right]);
            maxValue = Math.max(temp, maxValue);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxValue;
    }
}
// @lc code=end
