/*
 * @lc app=leetcode.cn id=338 lang=java
 *
 * [338] 比特位计数
 */

// @lc code=start
class Solution338 {
    /**
     * 思路：通过反复求余 计算1的个数
     * 看到测试用例才知道，有原生的整数求2进制1的个数方法 Integer.bitCount(6)
     * 
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = count1(i);
        }
        return res;
    }

    private int count1(int i) {
        int num = 0;
        while (i != 0) {
            num += i % 2;
            i /= 2;
        }
        return num;
    }

    /**
     * 看答案 动态规划的思路，f[i] = f[i>>1] + i&1
     * f[i] = f[i/2] + i%2 --> 按位运算 f[i] = f[i>>1] + i&1
     * 
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        // 先定义dp[num+1]
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++)
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }
}
// @lc code=end
