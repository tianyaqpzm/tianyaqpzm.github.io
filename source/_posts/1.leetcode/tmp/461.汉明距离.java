/*
 * @lc app=leetcode.cn id=461 lang=java
 *
 * [461] 汉明距离
 */

// @lc code=start
class Solution461 {
    /**
     * 思路： 先计算出二机制字符串，因为要对齐遍历，先翻转 然后一个个比较，得出结果
     * 
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance1(int x, int y) {
        String binaryX = Integer.toBinaryString(x);
        String binaryY = Integer.toBinaryString(y);
        int total = Math.max(binaryX.length(), binaryY.length());
        int res = 0;
        binaryX = reverse(binaryX);
        binaryY = reverse(binaryY);
        for (int i = 0; i < total; i++) {
            if (i < binaryX.length() && i < binaryY.length()) {
                if (binaryX.charAt(i) != binaryY.charAt(i)) {
                    res += 1;
                }
            } else {
                if (i < binaryX.length() && binaryX.charAt(i) == '1') {
                    res += 1;
                }
                if (i < binaryY.length() && binaryY.charAt(i) == '1') {
                    res += 1;
                }
            }
        }
        return res;
    }

    /**
     * 直接通过位运算符计算，汉明距离就是异或结果，将循环 其与1相与后 求和得到优多少个1
     * 关键点：想通等价于 异或， 并且知道如何快速求 二进制中1的个数
     * 
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int newValue = x ^ y;
        // int res = 0;
        // while (newValue != 0) {
        // res += newValue & 1;
        // newValue = newValue >> 1;
        // }
        return Integer.bitCount(newValue);
    }

    private String reverse(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            res.append(str.charAt(i));
        }
        return res.toString();
    }
}
// @lc code=end
