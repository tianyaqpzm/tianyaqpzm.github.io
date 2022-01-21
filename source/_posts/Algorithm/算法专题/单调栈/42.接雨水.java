import java.util.Stack;

/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 */

// @lc code=start
class Solution {
    public int trap1(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) {
                continue;
            }
            // 寻找左侧高度
            int leftHeight = height[i];
            int left = i - 1;
            while (left >= 0) {
                if (height[left] > leftHeight) {
                    leftHeight = height[left];
                }
                left--;
            }

            // 寻找右侧高度
            int rightHeight = height[i];
            int right = i + 1;
            while (right < height.length) {
                if (height[right] > rightHeight) {
                    rightHeight = height[right];
                }
                right++;
            }
            int h = Math.min(leftHeight, rightHeight) - height[i];
            if (h > 0) {
                res += h;
            }
        }
        return res;
    }

    /**
     * maxLeft[i] = max(height[i], maxLeft[i - 1]);
     * 
     */
    public int trap2(int[] height) {
        int length = height.length;
        if (length <= 2)
            return 0;
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        // 记录每个柱子左边柱子最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < length; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        // 记录每个柱子右边柱子最大高度
        maxRight[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        // 求和
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (count > 0)
                sum += count;
        }
        return sum;
    }

    /**
     * 
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int sum = 0;
        for (int i = 1; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int mid = stack.pop();
                if (!stack.isEmpty()) {
                    int h = Math.min(height[stack.peek()], height[i]) - height[mid];
                    int w = i - stack.peek() - 1;
                    sum += h * w;
                }
            }
            stack.push(i);
        }
        return sum;
    }

    // public static void main(String[] args) {
    // Solution42 solution42 = new Solution42();
    // solution42.trap(new int[] { 4, 2, 0, 3, 2, 5 });

    // }

}
// @lc code=end
